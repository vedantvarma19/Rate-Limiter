local key = KEYS[1]
local now = tonumber(ARGV[1])
local windowMs = tonumber(ARGV[2])
local capacity = tonumber(ARGV[3])

local clearBefore = now - windowMs

redis.call('zremrangebyscore', key, 0, clearBefore)

local currentRequests = redis.call('zcard', key)

local allowed = false

if currentRequests < capacity then
    redis.call('zadd', key, now, now)
    currentRequests = currentRequests + 1
    allowed = true
end

redis.call('pexpire', key, windowMs)

local remaining = math.max(0, capacity - currentRequests)

return { allowed and 1 or 0, remaining }
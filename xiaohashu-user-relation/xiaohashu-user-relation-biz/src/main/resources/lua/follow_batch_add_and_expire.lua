-- 操作的 Key
local key = KEYS[1]

-- 准备批量添加数据的参数 创建一个空表zaddArgs来存储批量添加的数据
local zaddArgs = {}

-- 遍历 ARGV （ARGV是从外部传入的参数数组）参数，将分数和值按顺序插入到 zaddArgs 变量中
for i = 1, #ARGV - 1, 2 do
    table.insert(zaddArgs, ARGV[i])      -- 分数（关注时间） 分数（时间戳）插入到zaddArgs表中
    table.insert(zaddArgs, ARGV[i+1])    -- 值（关注的用户ID） 保证了每个时间戳后面跟着一个用户ID
end

-- 调用 ZADD 批量插入数据 unpack函数用于展开zaddArgs表，使其成为多个参数传递给ZADD命令
redis.call('ZADD', key, unpack(zaddArgs))

-- 设置 ZSet 的过期时间
local expireTime = ARGV[#ARGV] -- 最后一个参数为过期时间
redis.call('EXPIRE', key, expireTime)

return 0
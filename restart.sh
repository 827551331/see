#!/bin/bash

# 停止旧的应用程序进程
echo "Stopping old application..."
sudo systemctl stop see.service

# 等待一段时间确保进程已停止
sleep 5

# 启动新的应用程序进程
echo "Starting new application..."
sudo systemctl start see.service

# 等待一段时间确保进程已启动
sleep 10

# 检查新的应用程序进程是否正在运行
if pgrep -f see.jar > /dev/null
then
    echo "Application restarted successfully."
else
    echo "Application failed to restart."
fi
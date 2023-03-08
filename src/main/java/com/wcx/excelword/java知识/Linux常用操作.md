1.目录

- root 超级管理员所在的目录
- Java程序员需要关心的：home、usr、tmp、etc
---
2.文件夹操作
- 进入文件夹和退出文件夹
  - cd 文件夹的名称
  - cd .. 退出当前文件夹
- 显示文件夹中的内容
  - ls 显示内容
  - ll 显示详细内容(为ls -l的缩写)
- 创建和删除文件夹
  - mkdir 文件夹的名称
  - rm -rf 文件夹的名称 （r表示循环迭代、f强制）
---
3.文件操作：
- touch 1.txt 创建一个文件
- 创建文件和编辑文件(vi)
  - vi/vim 文件的名称 如果文件不存在就会新建，如果文件存在就会打开进入。
    - 进入可编辑：点击 i 按键
    - 退出编辑状态： 点击esc按键
    - 退出文件：
      - :q 没有做任何修改退出
      - :wq 保存并退出
      - :q！ 强制退出
    - 3yy复制3行， p粘贴
    - 4dd删除4行
    - o表示跳到下一行（新行），并进入编辑模式
    - /查找
    - ^表示跳到行首字符，$表示跳到行尾字符
- 阅读文件
  - cat：适合阅读小文件
  - more：可以分页，适合大文件。但是只能向下翻页
  - less：可以上下翻页，进入到文件内存进行阅读，退出less是点击q按键，可以查询文件中的内容
    - ?查询的内容：向上查询数据
    - /查询的内容：向下查询数据
- 查询文件或者查询指定文件中的内容
  - find -name 文件名称 指定目录 （查询包括子文件夹中的内容）
  - find 文件名称 （查询当前文件夹下的内容，不包含子文件夹）
  - grep 查询的内容 指定的文件
---
4.查询命令所在目录：
which 命令名称
---
5.复制和移动：
- 复制（拷贝粘贴）
  - cp srcFile destDir
  - cp srcFile destDir/fileName
- 移动（剪切粘贴）
  - mv srcFile destDir
  - 如果在当前文件夹中移动，相当于重命名

[注意：]()无论是复制还是移动，如果目的为一个目录路径，则不改名

---
6.压缩和解压：
- - windows系统中压缩文件通常为（rar,zip）,Linux系统压缩文件为（test.tar.gz）
  - tar -zxvf gzFile 解压缩
  - tar -zcvf gzFileName srcFile 压缩
  - 指令说明
    - z：采用gzip进行压缩或者解压缩，gzip是Linux中的压缩工具包。
    - x：解压缩
    - c：压缩
    - v：压缩或者解压缩过程中显示详细的压缩或者解压缩的过程
    - f：文件（压缩文件)
---
7.其他命令：
pwd：查看当前文件夹全路径

bin->usr/bin，软链接，类似于windows的快捷方式，即两个路径操作的是同一个文件夹

./ 执行sh文件

---
8.Linux系统命令：
```
arch 显示机器的处理器架构
uname -m 显示机器的处理器架构
uname -r 显示正在使用的内核版本 
dmidecode -q 显示硬件系统部件 - (SMBIOS / DMI) 
hdparm -i /dev/hda 罗列一个磁盘的架构特性 
hdparm -tT /dev/sda 在磁盘上执行测试性读取操作 
cat /proc/cpuinfo 显示CPU info的信息 
cat /proc/interrupts 显示中断 
cat /proc/meminfo 校验内存使用 
cat /proc/swaps 显示哪些swap被使用 
cat /proc/version 显示内核的版本 
cat /proc/net/dev 显示网络适配器及统计 
cat /proc/mounts 显示已加载的文件系统 
lspci -tv 罗列 PCI 设备 
lsusb -tv 显示 USB 设备 
date 显示系统日期 
cal 2007 显示2007年的日历表 
date 041217002007.00 设置日期和时间 - 月日时分年.秒 
clock -w 将时间修改保存到 BIOS 
```


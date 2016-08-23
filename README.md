#Fe_WebViewTool
##简介
该项目可以提供前端开发人员调试web应用，目前提供了:

1.*扫码跳转页面*

2.*网址输入跳转页面*

3.*提供***eruda.min.js***动态注入webview*

4.*chrome浏览器usb调试*

##原理
1.使用js注入，将eruda.mini.js库动态注入到页面当中。

2.检测浏览器webgl兼容性参考:[Mozilla](https://developer.mozilla.org/en-US/Learn/WebGL/By_example/Detect_WebGL)

3.扫码使用zxing

4.WebView开起了浏览器调试功能,可使用chrome浏览器调试页面(需要usb连接,且**android os >= android 4.4**)

##Feature

1.*清除缓存*

2.*使用引导*

3......(欢迎提建议)

##使用说明
###1.扫码(没什么好说的)
###2.输入框(没什么好说的)
###3.调试模式
**3.1** 开启和关闭都会刷新当前页面,在加载完成之后会注入eruda.js,关于eruda的使用请走 [传送门](https://github.com/liriliri/eruda/blob/master/doc/Readme_CH.md)

**3.2** 使用usb连接手机即可在pc chrome浏览器下调试页面了,强烈推荐使用此方式进行开发调试. 

**step 1**

使用usb连接pc

**step 2**

打开chrome浏览器,进入开发者工具

**step 3**

如下图依次进入

![Image][7]

![Image][8]

![Image][9]

OK, 开始调试吧(**ps**: 请记得手机上把usb调试功能打开)

###4.自动刷新

开启此功能将实时监测代码变动,后台轮询请求文件,根据文件最后修改时间来动态替换掉旧资源.这里总感觉效率不高,也不知道会有什么坑.踩到坑的
麻烦提个Issues😂.

###5.历史
没啥好说的,寻找以前的记录,这部分感觉可以优化显示日期,暂时先这么多吧

###6.下载

👉[地址](http://fir.im/usvf)👈

##ShotScreen
![Image][0]

![Image][1]

![Image][2]

![Image][3]

![Image][4]

![Image][5]

![Image][6]

[0]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/0.pic.jpg
[1]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/1.pic.jpg
[2]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/2.pic.jpg
[3]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/3.pic.jpg
[4]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/4.pic.jpg
[5]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/5.pic.jpg
[6]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/6.pic.jpg
[7]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/step01.png
[8]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/step02.png
[9]: https://raw.githubusercontent.com/liang3472/Fe_WebViewTools_Pro/master/screenshot/step03.png


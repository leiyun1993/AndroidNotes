
### 1、MVP说明

MVP跟MVC很相像，因为MVP也是三层，唯一的差别是Model和View之间不进行通讯，都是通过Presenter完成。在android中由于activity（god object）的存在，Controller和View很难做到完全解耦。但在MVP中就可以很好的解决这个问题
看下MVP的设计图：
 ![image](https://github.com/leiyun1993/AndroidNotes/raw/master/image/mvp.png)
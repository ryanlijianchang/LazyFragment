# 一、背景 #

在Android应用中，ViewPager是我们不可避免使用的一个控件，因为它可以使我们在占用较少空间的同时，增强内容的丰富性，同时以其内部流淌着Google的血液，所以它几乎成了每一个App的标配控件。但是，假如ViewPager的每一个Fragment都需要通过网络拉取数据加载，而ViewPager是默认加载前两项的，所以在很容易造成网络丢包或者网络堵塞等问题，所以Fragment使用懒加载是非常有必要的。

举个栗子：

![image](http://onq81n53u.bkt.clouddn.com/s.jpg)

如上图所示，我们有两个大的Tab：人物和风景。而人物Tab下有三个Tab：美女、帅哥、萌娃三个Tab，风景Tab下有：北京、香港、上海三个Tab。假如当App刚启动时，执行的生命周期如下：

![image](http://onq81n53u.bkt.clouddn.com/ssss.jpg)

我们可以看到，App会默认加载美女和帅哥两个Fragment，并且它们的生命周期都执行到onStar（）方法，同时加载两个Fragment，假如我们在Fragment加载的时候拉取网络数据，那么就会造成如上所说的网络丢包或者网络堵塞等问题，所以我们为了避免这个问题，就需要实现Fragment的懒加载，当我们对Fragment可见的时候，再进行网络加载数据。

# 二、实现Fragment懒加载 #

针对背景讨论的问题，我们最终要实现的目标就是，当Fragment对我们可见时，我们才进行网络加载，然后再解析数据，更新UI。针对上面的Demo，我们需要做到如下效果：

![image](http://onq81n53u.bkt.clouddn.com/pic11111.gif)

要实现起来其实也并不复杂，在Fragment中有一个`setUserVisibleHint`这个方法，而且这个方法是优于onCreate()方法的，所以也可以作为Fragment的一个生命周期来看待，它会通过isVisibleToUser告诉我们当前Fragment我们是否可见，我们可以在可见的时候再进行网络加载。
```
public void setUserVisibleHint(boolean isVisibleToUser)
```

当我们在`setUserVisibleHint`方法中进行Log输出时，我们可以看到：

![image](http://onq81n53u.bkt.clouddn.com/70970-a0b03ff409e905014643d270342b8634.jpg)

只有可见时，我们isVisibleToUser为true，否则为false。所以我们可以重写`setUserVisibleHint`方法，然后在可见时进行网络加载数据：


```
@Override
public void setUserVisibleHint(boolean isVisibleToUser) {
    Log.d("TAG", mTagName + " setUserVisibleHint() --> isVisibleToUser = " + isVisibleToUser);

    if (isVisibleToUser) {
        pullData();
    }
    super.setUserVisibleHint(isVisibleToUser);
}
```


# 三、根据实际用途使用懒加载 #

由第二部分我们可以知道，`setUserVisibleHint(boolean isVisibleToUser)`方法是比`onCreate`更早调用的，但是我们一般在加载数据时，都会在数据加载完成时进行UI更新，所以这就有了一个问题，假如拉取数据是秒回，但是我们还没有进行UI绑定，或者是Adapter初始化等，那么我们就无法更新UI了，所以Fragment给我们提供了另一个方法`getUserVisibleHint()`，它就是用来判断当前Fragment是否可见，所以我们就可以在一系列变量初始化完成后再判断是否可见，若可见再进行数据拉取：


```
@Override
public void onStart() {
    super.onStart();
    Log.d("TAG", mTagName + " onStart()");

    ...

    if(getUserVisibleHint()) {
        pullData();
    }

}
```

当然，如果你的网络请求并不需要涉及UI更新，那么就可以直接在`setUserVisibleHint(boolean isVisibleToUser)`里操作，所以最终还是要根据各自的实际用途来使用。

最后，附上懒加载的Demo：[Github/LazyFragment](https://github.com/ryanlijianchang/LazyFragment)


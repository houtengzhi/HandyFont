# HandyFont
>

[ ![Download](https://api.bintray.com/packages/houtengzhi/maven/HandyFont/images/download.svg) ](https://bintray.com/houtengzhi/maven/HandyFont/_latestVersion)

>

### Installation

Add jcenter repo:

```
allprojects {
    repositories {
        jcenter()
    }
}
```

Add dependency:

```
implementation 'com.yechy.handyfont:handyfont:1.0.1'
```

### Usage

Initial
```
HandyFontConfig.getInstance()
                .setLogEnabled(true)
                .setDebugEnabled(true)
                .setReplaceEnabled(true)
                .addReplaceDefaultFont(dancingScript_bold)
                .addReplacedFont(sans_serif, chilanka_regular)
                .addReplacedFont(sans_serif_medium, dancingScript_regular);
```

Replace the ```Activity``` contextwrapper with HandyContextWrapper:
```
@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(HandyContextWrapper.wrap(newBase, this));
    }
```
or

```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        HandyFontLayoutFactory factory = new HandyFontLayoutFactory(this);
        factory.installViewFactory();
        super.onCreate(savedInstanceState);
    }
```


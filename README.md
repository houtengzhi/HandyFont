# HandyFont
>

[ ![Download](https://api.bintray.com/packages/houtengzhi/maven/HandyFont/images/download.svg) ](https://bintray.com/houtengzhi/maven/HandyFont/_latestVersion)

> HandyFont is a library to replace font in Android.

## Installation

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

## Usage

Firstly some configurations need to be setted, you can do it in your ```Application```. 

```java
HandyFontConfig.getInstance()
                .setLogEnabled(true)
                .setDebugEnabled(true)
                .setReplaceEnabled(true)
                .addReplaceDefaultFont(dancingScript_bold)
                .addReplacedFont(sans_serif, chilanka_regular)
                .addReplacedFont(sans_serif_medium, dancingScript_regular);
```

Then replace the ```Activity``` contextwrapper with HandyContextWrapper:

```java
@Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(HandyContextWrapper.wrap(newBase, this));
    }
```

or

```Java
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        HandyFontLayoutFactory factory = new HandyFontLayoutFactory(this);
        factory.installViewFactory();
        super.onCreate(savedInstanceState);
    }
```

## Licence
```
    Copyright 2019 Cloud Ye
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
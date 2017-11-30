1 Intent分为两大类，显式和隐式。

显式事件，就是指通过 component Name 属性，明确指定了目标组件的事件。

比如我们新建一个Intent，指名道姓的说，此事件用于启动名为"com.silenceburn.XXXX”的Activity，那么这就是一个显式事件。

隐式事件，就是指没有 component Name 属性，没有明确指定目标组件的事件。

IntentFilter匹配原则

1. action 匹配
匹配规则：只要Intent中action能够和过滤规则中的任何一个action相同即可匹配成功
例如：
<activity android:name=".intentfilter.IntentActionActivity">
            <intent-filter>
                <action android:name="com.android.action.a"></action>
                <action android:name="com.android.action.b"></action>
                <category android:name="android.intent.category.DEFAULT"></category>
            </intent-filter>
        </activity>
2. Catogory匹配
Intent中如何出现了category，不管有几个，对于每个category来说，它必须是过滤规则中已经定义了的category。
Intent中可以没有category，系统会为其自动为其设置一个默认值，"android.intent.category.DEFAULT"，此时，如果我们的想正常使用隐式调用，则配置中必须添加
<category android:name="android.intent.category.DEFAULT"></category>
3. data匹配
data 由两部分组成，mimiType和Url。mimeType指媒体类型，如果image/jpeg、video/*等。url中包含的数据比较多，结构如下：
scheme://host:port/path|pathPrefix|pathPattern

path:完整的路径； 
pathPattern：表示完整的路径信息，但是它里面可以包含通配符"*"，“*”表示0个或者多个任意字符； 
pathPrefix：表示路径的前缀信息。

如果同时指定mimeType和Url，需要通过setDataAndType来同时设置，如果单独通过setDada和setUrl来设置，会互相清除对方的值。





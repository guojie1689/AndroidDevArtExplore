任务概念
任务是指在执行特定作业时与用户交互的一系列 Activity。 这些 Activity 按照各自的打开顺序排列在堆栈（即返回栈）中。
设备主屏幕是大多数任务的起点。当用户触摸应用启动器中的图标（或主屏幕上的快捷方式）时，该应用的任务将出现在前台。 如果应用不存在任务（应用最近未曾使用），则会创建一个新任务，并且该应用的“主”Activity 将作为堆栈中的根 Activity 打开。
明白任务栈的概念后，我们再来了解Activity的启动模式。目前Activity有四种启动模式：standard、singleTop、singleTask、singleInstance

standard：标准模式，这是系统默认的默认，也就是说你不设置Activity的launchMode时，默认的就是standard。在这种模式下，每次启动一个Activity都会重新创建一个新的实例，不管这个实例是否存在。
示例：两个界面A 和 B ，在A界面点击按钮启动B，在B界面点击按钮启动A，点击多次会看到最后的任务栈为 ABABAB

singleTop：栈顶复用模式。在这种模式下，如果Activity已经在任务栈的栈顶了，当再次启动同一个Activity的时候，这个Activity不会被重新创建，而且它的onNewIntent()方法会被调用，但是它的onCreate()、onStart()方法不会被调用。
例如：两个界面A和B，A界面点击启动B，B界面点击分两种情况：
1)  启动A界面，这时效果同standard，因为没有启动界面在栈顶，所以为ABABAB
2) 启动B界面，这时由于B界面已经在栈顶，则复用，最后任务栈为AB

singleTask：栈内复用模式。在这种模式下，只要Activity存在栈内，那么多次启动这个Activity都不会重新创建实例，系统会调用它的onNewIntent()方法。此外有个需要注意的地方：singleTask有clear top的效果，也就是说会将其以上的Activity全部出栈
例如：三个界面ABC，A界面启动B，B界面启动C，C界面点击启动A，最终点击完C界面后，由于任务栈中存在界面A，则调用其onNewIntent方法，同时将B和C移出任务栈。

这是singleTask的一种加强模式，除了singleTask所有特性以外，具有此模式的Activity只能单独位于一个任务栈中。换句话说，比如Activity A 是singleInstance模式，当A启动后，系统会为其创建一个新的任务栈，然后A独自在这个新的任务栈中，由于 栈内复用的特性，后续的情况均不会创建新的Activity，除非这个独特的任务栈被系统销毁了。

Activity 的 Flags
1三种启动标志:
* FLAG_ACTIVITY_NEW_TASK
会产生与 "singleTask" launchMode 值相同的行为； 
在新的任务栈中启动Activity，如果该任务栈中已经包含该Activity的实例，则该Activity的实例会被转到前台将恢复其状态，同时该Activity的onNewIntent()方法被调用
而经过实际验证，与singleTask还是有一定区别，由于singleTask本身自带ClearTop效果，但New_Task没有该效果
* FLAG_ACTIVITY_SINGLE_TOP
会产生与 "singleTop" launchMode 值相同的行为;
如果所要启动的Activity的任务栈中存在该Activity的实例，并且该实例位于栈顶，则调用Actiivty的onNewIntent()，而不再创建新的实例
* FLAG_ACTIVITY_CLEAR_TOP
如果当前所要启动的Activity所在任务栈中已经存在该Activity的实例，则销毁位于该实例上方的所有其它Activity的实例，并调用该Activity的onNewIntent()，而不创建新的实例。对于”standard”模式的Activity比较特殊，会清除掉所要启动的Activity的实例，并新建一个实例位于栈顶
* FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
具有该标记的Activity不会出现在历史Activity的列表中。
android:excludeFromRecents = "true"




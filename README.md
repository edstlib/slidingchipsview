# SlidingChipsView

![PagerNavigationView](https://i.ibb.co/LzrRFnF/slidingchipsview.png)

# GroupChipsView
![PagerNavigationView](https://i.ibb.co/v1PRWbJ/Screen-Shot-2021-09-17-at-17-17-47.png)

## Setup
### Gradle

Add this to your project level `build.gradle`:
```groovy
allprojects {
 repositories {
    maven { url "https://jitpack.io" }
 }
}
```
Add this to your app `build.gradle`:
```groovy
dependencies {
    implementation 'com.github.edtslib:slidingchipsview:latest'
}
```
# Usage

The SlidingChipsView is very easy to use. Just add it to your layout like any other view.
##### Via XML

Here's a basic implementation.

```xml
    <id.co.edtslib.slidingchipsview.SlidingChipsView
        android:id="@+id/chips"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```
Here's to give list data to slidingchipsview

```kotlin
        val list = mutableListOf("Abah", "Hezbi", "Ade", "Robert", "Jovan", "Ucup")
        val chips = findViewById<SlidingChipsView<String>>(R.id.chips)
        chips.items = list
```

### Attributes information

##### _app:slideChipMargin_
[dimension]: margin start end end with parent, default 16dp

##### _app:slideChipTextPadding_
[dimension]: start and text text padding text and chip, default 20dp

##### _app:slideChipTextColor_
[integer]: resource id color of chip text, default

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- text color when chip is selected -->
    <item android:state_selected="true"
        android:color="#ffffff" />
    <!-- text color when chip is normal -->
    <item android:color="#1171D4"/>
</selector>
```

##### _app:slideChipBackground_
[integer]: resource id color of chip background, default

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <!-- background when chip is selected -->
    <item android:state_selected="true"
        android:color="#1171D4" />
    <!-- background when chip is normal -->
    <item android:color="#EFF3F6"/>
</selector>
```

### Listening for click actions on the SlidingChipsView

You can set a listener to be notified when the user click the SlidingChipsView. An example is shown below.

```kotlin
        val chips = findViewById<SlidingChipsView<String>>(R.id.chips)
        chips.delegate = object : SlidingChipsDelegate<String> {
            override fun onSelected(item: String, position: Int) {
                Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT).show()
            }

        }
```

### Method for navigation actions on the SlidingVhipsView


```kotlin
    // selected index of chip
    var selectedIndex: Int = 0
```





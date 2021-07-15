### `TITLE:` GETTING STARTED WITH JETPACK COMPOSE IN ANDROID
### `AUTHOR:` [MAURINE MUTHOKI](https://github.com/MaurineM)

| COPYRIGHT :copyright: | MAURINE                                                                          |
| --------------------- | -------------------------------------------------------------------------------- |
| PUBLISHER             | [SECTION.IO](https://section.io/engineering-education)                           |
| CC                    | [ENG-ED](https://www.section.io/engineering-education/getting-started-with-jetpack-compose-in-android/) |    

### Introduction to Jetpack Compose
Android’s imperative UI design paradigm is over 11 years old at the time of writing this article😱. Over this time, a lot of issues have been raised concerning its overall performance. Android developers wished that the old view system and `XML` layouts were no longer an issue.
<!--more-->
Jetpack Compose is a contemporary toolkit for Android that makes UI development easier. It's totally declarative, which means you can describe your user interface by invoking a set of composables.

This article will walk you through the fundamental concepts that you need to get started with this new and quick development paradigm.

### Why learn Jetpack Compose?
Compose has the following benefits:

1. It is very fast and offers a smooth performance.
2. It's simple to learn.
3. It is possible to interoperate with an imperative approach.
4. Offers a better way to implement loose coupling principles.
5. It is 100% made in Kotlin which makes it a modern approach in Android development.

### Prerequisites
To follow through this tutorial, you need the following:
- Good understanding of the `Kotlin` programming language.
- Android studio `Canary` or `Beta` version installed on your machine.

>**NOTE:** At the time of writing this article, Compose is still in `Beta`, meaning it's not yet available on the `Stable` channel of Android Studio.
For this reason, we're going to install Android Studio 2020.3.1 `Beta 4` (or later). Both Beta and Canary version have support for Compose. However, I'd recommend you to use Beta(from arctic fox distribution) over the Canary version(Bumblebee) due to some weird bugs I've personally experienced with it.
### Installing Android Studio Beta
(Assuming that you're running on Linux or Mac).

Head to the [official website](https://developer.android.com/studio/preview) and grab the latest version of the IDE. Upon downloading, extract the file and run `studio.sh` file located at `/android-studio/bin/studio.sh`. Leave everything as default and wait for it to finish. On Mac, drag and drop the unpacked file `~/android-studio` into the applications folder and launch it. For other OS users, please refer to [this](https://androidstudio.googleblog.com/) for further guidance.

### Creating a Compose project
Launch the IDE and select the `Empty Compose Activity` template in the new project panel. Give your project any name and hit next/finish.
![New Compose Project](/engineering-education/getting-started-with-jetpack-compose-in-android/new-compose-project.png)

### New-Project structure
Once build finishes, the `MainActivity` file opens up. This serves as the entry point for our Application.

#### Explaining the Autogenerated code
Before we proceed, it's good to understand the code that comes with the Compose template.

```Kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // Composables are called here
                }
            }
        }
    }
}
```
The `MainActivity` class inherits from `ComponentActivity()` which is a building block for Jetpack Compose components. The `setContent` lambda determines what will be displayed on the screen at runtime. It makes use of the Material theme when calling Composable functions (discussed below).

### Composable function
A Composable function is a tool that transforms data into a UI hierarchy. It's just like a regular Kotlin function only that it is annotated with `@Composable` annotation. 

Syntax:
```Kotlin
@Composable
fun FunctionName(){
    
}
```
Example:
```kotlin
@Composable
fun HelloWorld() {
    // body
}
```
A Composable function can take parameters of any type if need be. Notice that the function's name starts with an uppercase letter. Otherwise, the IDE yells at you! raising a warning over the same. 

>NB: A composable function can not be called in a non composable function.
When working with composable functions that accept arguments, it is recommended to use named parameters strategy to let the compiler know which properties are being implemented since we may not use all available properties.

### Displaying Previews
Previews give the developer a clue on how to expect the app to look at runtime. In compose, a preview is created by annotating a composable function with `@Preview` annotation. You can have as many previews as you wish especially when you want to test different UI components in different composables at the same time.

### Creating Composable user interface elements
UI elements play a paramount role in an app. In most cases, users give their feedback based on how the UI looks like. Compose has different techniques for creating these elements more concisely.

#### Text
This is an inbuilt composable function that displays a string provided in its parameter.

```kotlin
@Composable
fun TextExample(){
    Text(text = "Hello Android")
}
```
Here we can set text properties such as `color`, `font style`, `fontsize`, `alignment` etc.
```kotlin
Text(text = "Hello Android", color = Color.Blue, textAlign = TextAlign.Center)
```

Other than a plain string, a Text accepts a decorated string otherwise referred to as an annotated string. This is done using an annotation builder as shown below.
```Kotlin
@Composable
fun AnnotatedString() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append("H")
            }
            append("ello ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Blue)) {
                append("A")
            }
            append("ndroid")
        }
    )
}
```
This produces a colored text.

The repeat function can be used to duplicate a text instead of creating multiple independent Texts.
```Kotlin
@Composable
fun RepeatText() {
    Text("Android ".repeat(30), maxLines = 3)
}
```
This creates a text "Android" and repeats it 30 times. The `maxLines` attribute determines the maximum number of lines a text can take regardless of repeated times.

### Button
This is a composable function that displays a button on the screen. It's unusual to come across an app that doesn't have any buttons!
```Kotlin
@Composable
fun ButtonExample(){
    Button(text = “Next”, onClick = {
    // handle a click event here
})
}
```
A button can be advanced by adding a style as a parameter.
```Kotlin
Button(style = TextButtonStyle(contentColor = Color.White))
```

### Image
Images are one of the components that makes an app appealing. Compose uses the `Image` function and a `painter` (the actual image resource) to display an image.
```Kotlin
@Composable
fun ImageExample(){
    val painter = painterResource(R.drawable.your_drawable)
    Image(
    painter = painter,
    contentDescription = "image description")
}
```
A content description is optional but it's very important especially to visually impaired users. Similarly, we can style an image by providing parameters such as contentScale, modifier, etc. Click `Command (⌘) `+`P` to see all the possible parameters.

### TextField
A text field is used to take user inputs. This input could be a number, password, or plain string.

```Kotlin
@Composable
fun TextFieldExample() {
        var textState by remember { mutableStateOf(TextFieldValue()) }
        TextField(
                value = textState.value,
                onValueChange = { textState.value = it }
        )
}
```
The textState variable is a remembered state, meaning that its value is maintained during [recomposition](#states-and-intelligent-recomposition). Without it, characters won't appear when typing.

### OutlinedTextField
This is an advanced TextField whose label 'jumps' to the outline of the field once the user starts typing.

```Kotlin
@Composable
fun OutlinedTextFieldExample() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}
```
A visual transformation determines the input type of a TextField. For instance, to request a password input, we'd use;

```Kotlin
TextField(
    visualTransformation = PasswordVisualTransformation()
    )
    // the same applies to an OutlinedTextField
```

### Modifier
A composable's appearance can be decorated using a property function known as a 'modifier'. Any inbuilt composable can be modified using a modifier. When using a series of modifiers, the results of the previous one become the input of the next one. For this reason, we should be very keen on the order of modifiers.

 Let's take an image for example.

```Kotlin
fun ModifiedImage{
    Image(
        painter = painterResource(R.drawable.your_drawable)
        modifier = Modifier
            .border(3.pd, color = Color.Cyan, shape = CircleShape)
            .padding(2.dp)
            .clip(shape = CircleShape)
    )
}
```
#### Reusing modifiers
A modifier can be applied to multiple elements for similar modifications. This can be achieved through;
#### i). Passing a modifier as a parameter in a Composable function
```kotlin
@Composable
fun Example(modifier: Modifier = Modifier){
    // multiple components can use `modifier` argument as their base Modifier
    Image(
        ...
        modifier = modifier
    )
    // a modifier can also be expanded
    Text(
        ...
        modifier = modifier.//a modifying property
    )
}
```
#### ii). Declaring a modifier variable
```kotlin
@Composable
fun Example(){
    val modifier: Modifier = Modifier
    // 
    Image(
        ...
        modifier = modifier
    )
}
```

### Reusing Composables
A high-order function that accepts a composable function as an argument can be used to reuse other composables. This helps to keep your code DRY.

Call our function and pass a composable in its lambda.
```Kotlin
@Composable
fun CallFun(){
    HighOrder{
        ReuseDemo()
    }
}
```
Create a high order function.
```Kotlin
@Composable
fun HighOrder(composeFun: @Composable () -> Unit){
}
```
Create a function to be reused.
```Kotlin
@Composable
fun ReuseDemo(){
}
```
You may have realized that Compose builds on this approach. For instance, a button accepts `onClick` lambda whose value is a function.

### Surfaces
A surface is a scope in which components lay on the screen.
```Kotlin
@Composable
fun SurfaceDemo(){
    Surface(color = Color.Blue){ // decorate a surface
        // call composable functions here
    }
}
```

### Compose Layouts
You may have noticed that when you use more than one UI composable, the elements are stack on each other! Compose layouts allows us to arrange components with respect to each other.

#### Row
A row lays items horizontally. The order of appearance of an item is determined by its position in the code block.
```Kotlin
@Composable
fun RowExample(){
    Row{
        Text(text = "Hello ")
        Text(text = " Jetpack ")
        Text(text = "Compose")
    }
}
```
#### Columns
A column arranges items vertically.
```Kotlin
@Composable
fun RowExample(){
    Column{
        Text(text = "Hello")
        Text(text = "Jetpack")
        Text(text = "Compose")
    }
}
// A layout is also a composable function, so, it can have a modifier that
// applies to all its children.
```
#### Box
This is similar to Frame Layout in the imperative design.
```Kotlin
@Composable
fun BoxExample(){
    Box{
        // put items here
    }
}
```

If your items get out of view, you can use the `Modifier.horizontalScroll` or `Modifier.verticalScroll` modifiers to enable the respective scrolling mode.

### States and intelligent Recomposition
A `state` is a value that has the potential to change over time. In compose the UI is immutable in that it can only be fully drawn/composed once. However, when states of some parts of the UI change, compose recreates them. This is referred to as intelligent `recomposition`. During this process, we need to persist our states for the updates to be effective. This is where `remember` comes in. Remember is a [property delegate](https://kotlinlang.org/docs/delegated-properties.html) used to persist immutable and mutable objects.

```Kotlin
...
var myState by remember{mutableStateOf("Hello")}
```
We use this state to observe events or get their value at any given time.

### State Hoisting
State hoisting deals with making stateful composables stateless meaning that a composable can then not change any state itself.
State hoisting improves the code readability and promotes unit directional flow of data in one direction. This way, debugging is made easier. 
You can learn more about state hoisting [here](https://youtu.be/GT1VJweyNr0).

### Conclusion
In this article, we've covered the core concepts of the declarative paradigm in Android.
Jetpack Compose is still very young and a lot of updates are being uploaded now and then.
For this reason, I'd recommend you to regularly keep checking the [release notes](https://developer.android.com/jetpack/androidx/releases/compose) to stay updated. However, Compose is ready for its stable `version 1.0` which will be released on 10th July 2021. At Google I/O 2021, Google announced that its framework is getting ready for the new design language referred to as **Material You**.

Happy Composing!😊

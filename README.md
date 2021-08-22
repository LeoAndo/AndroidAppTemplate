# Overview
This project is "Android Template Project" for small-scale development.<br>

# Development environment

<img width="646" alt="スクリーンショット 2021-06-14 13 38 53" src="https://user-images.githubusercontent.com/16476224/127751156-0638bd97-e532-43e9-be12-e758a7118141.png">

[gradle version](https://github.com/LeoAndo/AndroidAppTemplate/blob/main/gradle/wrapper/gradle-wrapper.properties)<br>

# Architecture
MVVM + SingleActivity + Multi Module<br>
<img width="1029" alt="スクリーンショット 2021-06-21 15 43 26" src="https://user-images.githubusercontent.com/16476224/122718200-9d939b80-d2a7-11eb-8f02-69c1d16a0390.png">

# implementation Rules
- API KEY is managed as confidential information (`local.properties`)
- Presentation layer
  - Error handling is done by Activity or Fragment.
  - Fragment does not have Toolbar.
  - If simple logic, Call Repository (Interface) directly from ViewModel.
  - Since strings.xml is faster to grow, separate files by features.
- domain layer
  - Pure Kotlin.
  - hold Model(Serialize).
- data layer
  - Specify the Dispatcher.
  - One shot processing throws an Exception individually.
  - Coroutines Flow etc. return the processing result in the form of Sealed interface. (Reference: Paging3)
  - Module division to make it easier to understand the library used for each features.
- Proguard / R8
  - [Use only stable libraries](https://github.com/LeoAndo/AndroidAppTemplate/issues/40#issue-925121453)
# Library
[see this file](https://github.com/LeoAndo/AndroidAppTemplate/blob/main/outputs/sdk-dependencies/release/sdkDependencies.txt)

# Usage
Set API KEY in `local.properties` .<br>
<img width="1197" alt="スクリーンショット 2021-08-22 23 23 37" src="https://user-images.githubusercontent.com/16476224/130358585-43db8d31-e586-4c7a-8804-64c59b90f37d.png">

# reference
[hilt-multi-module](https://developer.android.com/training/dependency-injection/hilt-multi-module?hl=ja)<br>
[Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)<br>
[Android-Kotlin-Clean-Architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/sample/src/main/java/com/sanogueralorenzo/sample)<br>


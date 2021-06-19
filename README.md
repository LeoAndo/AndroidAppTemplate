# Overview
This project is "Android Template Project" for small-scale development.<br>

# Development environment

<img width="646" alt="スクリーンショット 2021-06-14 13 38 53" src="https://user-images.githubusercontent.com/16476224/121839875-f8604c80-cd15-11eb-99be-d3d2829a3f07.png">

gradle version 4.2.1<br>

# Architecture
MVVM + SingleActivity + Multi Module<br>
<img width="1022" alt="スクリーンショット 2021-06-20 1 57 55" src="https://user-images.githubusercontent.com/16476224/122649853-f0b40400-d16a-11eb-8adc-3b47376e84ec.png">

# implementation Rules
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
- Proguard / R8
  - [Use only stable libraries](https://github.com/LeoAndo/AndroidAppTemplate/issues/40#issue-925121453)
# Library
[see this file](https://github.com/LeoAndo/AndroidAppTemplate/blob/main/app/release/outputs/sdk-dependencies/release/sdkDependencies.txt)

# Usage
It is necessary to set the API KEY of each service to operate as an application.<br>
https://github.com/LeoAndo/AndroidAppTemplate/blob/main/data/build.gradle#L5:L10<br>

# reference
[hilt-multi-module](https://developer.android.com/training/dependency-injection/hilt-multi-module?hl=ja)<br>
[Android-CleanArchitecture](https://github.com/android10/Android-CleanArchitecture)<br>
[Android-Kotlin-Clean-Architecture](https://github.com/sanogueralorenzo/Android-Kotlin-Clean-Architecture/tree/master/sample/src/main/java/com/sanogueralorenzo/sample)<br>


# Overview
This project is "Android Template Project" for small-scale development.<br>

# MAD Score

![summary](https://user-images.githubusercontent.com/16476224/150648011-24a3ea3d-c0ed-42e4-add9-11d8d1b8854a.png)
![jetpack](https://user-images.githubusercontent.com/16476224/150648006-0923a917-0276-4ad6-8872-192b4cf09a29.png)
![kotlin](https://user-images.githubusercontent.com/16476224/150648007-f0c1bca1-6b1f-49b9-b951-4c9305232225.png)
![studio](https://user-images.githubusercontent.com/16476224/150648009-dac660f0-ebe6-4c2f-8aac-abefec69d0b1.png)

# Development environment

<img width="646" alt="スクリーンショット 2021-06-14 13 38 53" src="https://user-images.githubusercontent.com/16476224/127751156-0638bd97-e532-43e9-be12-e758a7118141.png">

[gradle version](https://github.com/LeoAndo/AndroidAppTemplate/blob/main/gradle/wrapper/gradle-wrapper.properties)<br>

# Architecture
MVVM + SingleActivity + Multi Module<br>
<img width="1029" alt="スクリーンショット 2021-06-21 15 43 26" src="https://user-images.githubusercontent.com/16476224/122718200-9d939b80-d2a7-11eb-8f02-69c1d16a0390.png">

# implementation Rules
- API KEY is managed as confidential information (`local.properties`)
- Presentation layer
  - try catch is done in ViewModel.
  - ViewModel returns the processing result as Livedata.
  - It has the value of UiState (Loadinng, Throwable, Success) defined in the sealed interface as Livedata.
  - Monitor one Livedata with Activity / Fragment and update the screen according to the processing result.
  - Fragment does not have Toolbar.
  - If simple logic, Call Repository (Interface) directly from ViewModel.
- domain layer
  - Pure Kotlin.
  - hold Model(Serialize).
  - It should be a mechanism that can be safely called even from the main thread.
- data layer
  - Specify the Dispatcher IO.
  - If processing fails, throw a custom Exception according to the processing result.
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


# Youtube UI/UX Animation
This is a sample app demonstrating Youtube UX/UI animation using ConstraintLayout.It implements the Keyframe Animation feature in ConstrainLayout.This sample apps is build on Android Architecture Components and fully written in **Kotlin**.

## Features

  - Smooth Draggable Animation
  - Customizable Video Player
  - Dependency Injection (Dagger 2)
  - MVVM Architecture pattern using Android Architecture Components
  


## The Concept - Animation

Below image shows how the layout has been desgin using guidelines and all the Fragments are constraints to this guidelines. When user drag the video fragment we starts scaling up/down the guideline values which scale the fragments automatically that's the advantage of using constraint layout with guidelines

![](https://github.com/burhanrashid52/YoutubeAnimation/blob/master/gifs/the_concept.jpg)




## Customization

| Fragment | FrameLayout  | Usage |
| ------------- | ------------- | ------------- |
| HomeFragment , TradeFragment , LibraryFragment and UserActivityFragment | `frmHomeContainer`  | This layout hold Fragments added from BottomNavigation bar
| VideoPlayerFragment | `frmVideoContainer`  | This will have the video player  |
| VideoDetailsFragment | `frmDetailsContainer`  | Contains Video Details Layout Desgin |
| BaseBottomNavigationView | `bottomNavigation`  | Bottom Navigation bar |



## Architecture
The app uses ViewModel to abstract the data from UI and MovieRepository as single source of truth for data. MovieRepository first fetch the data from database if exist than display data to the user and at the same time it also fetches data from the webservice and update the result in database and reflect the changes to UI from database.

![](https://github.com/burhanrashid52/YoutubeAnimation/blob/master/gifs/archtiture.png)




## License
Copyright 2018 Burhanuddin Rashid

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

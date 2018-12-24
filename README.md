# Youtube UI/UX Animation
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![API](https://img.shields.io/badge/API-17%2B-brightgreen.svg)
[![Uplabs](https://img.shields.io/badge/Uplabs-YoutubeUX-orange.svg)](https://www.uplabs.com/posts/youtubeux)
[![AndroidWeekly](https://img.shields.io/badge/Android%20Weekly-%23305-blue.svg)](http://androidweekly.net/issues/issue-305)
[![AwesomeAndroid](https://img.shields.io/badge/Awesome%20Android-%2397-brightgreen.svg)](https://android.libhunt.com/newsletter/97)
[![Mindorks](https://img.shields.io/badge/Mindorks%20Newsletter-%237-red.svg)](https://mindorks.com/newsletter/edition/7)


This is a sample app demonstrating Youtube UX/UI animation using ConstraintLayout.It implements the Keyframe Animation feature in ConstrainLayout.This sample app is built on Android Architecture Components.

Proudly :muscle: made in [**Kotlin**](https://kotlinlang.org/)

## Features

  - Smooth Draggable Animation
  - Customizable Video Player
  - Dependency Injection (Dagger 2)
  - MVVM Architecture pattern using Android Architecture Components
  

## How does it looks

![](https://github.com/burhanrashid52/YoutubeUX/blob/master/gifs/youtube_ux_demo.gif)


This video demonstarte the app animation.

[![](https://img.youtube.com/vi/NSqsXxgbtPw/0.jpg)](https://www.youtube.com/watch?v=NSqsXxgbtPw)

[Direct Video Link](https://www.youtube.com/watch?v=NSqsXxgbtPw)
  


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


## How to contribute?
* Check out contribution guidelines 👉[CONTRIBUTING.md](https://github.com/burhanrashid52/YoutubeAnimation/blob/master/CONTRIBUTING.md)



## Questions?🤔
Hit me on twitter [![Twitter](https://img.shields.io/badge/Twitter-%40burhanrashid52-blue.svg)](https://twitter.com/burhanrashid52)
[![Medium](https://img.shields.io/badge/Medium-%40burhanrashid52-brightgreen.svg)](https://medium.com/@burhanrashid52)
[![Facebook](https://img.shields.io/badge/Facebook-Burhanuddin%20Rashid-blue.svg)](https://www.facebook.com/Bursid)


## Resources
  - [Youtube UI/UX](https://www.uplabs.com/youtube)
  - [Constraint Layout](https://constraintlayout.com)  
  - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)
  - [Android New Sample](https://github.com/AkshayChordiya/android-arch-news-sample)


## Credits
Its inspired from [Youtube Android App](https://play.google.com/store/apps/details?id=com.google.android.youtube&hl=en_IN)


## License
Copyright 2018 Burhanuddin Rashid

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

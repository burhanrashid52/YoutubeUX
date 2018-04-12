# Youtube UI/UX Animation
This is a sample app demonstrating Youtube UX/UI animation using ConstraintLayout.It implements the Keyframe Animation feature in ConstrainLayout.This sample apps is build on Android Architecture Components and fully written in **Kotlin**.

## Features

  - Smooth Draggable Animation
  - Customizable Video Player
  - MVVM Architecture pattern using Android Architecture Components
  

## The Concept - Animation

Below image shows how the layout has been desgin using guidlines and all the Fragments are constraints to this guidlines.When user drag the video fragment we starts scaling up/down the guideline values which scale the fragments automatically that's the advantage of using constraint layout with guidlines

![](https://github.com/burhanrashid52/YoutubeAnimation/blob/master/gifs/the_concept.jpg)


## Customisation

| Fragment | FrameLayout  | Usage |
| ------------- | ------------- | ------------- |
| HomeFragment | `frmHomeContainer`  | This layout hold Fragments added from BottomNavigation
| VideoPlayerFragment | `frmVideoContainer`  | This will have the video player  |
| VideoDetailsFragment | `frmDetailsContainer`  | Contains Video Details Layout Desgin |
| BaseBottomNavigationView | `bottomNavigation`  | Bottom Navigation bar |


## License
Copyright 2018 Burhanuddin Rashid

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

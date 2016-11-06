# ForceTouch
<h1 align="center"><img src="https://s13.postimg.org/rodwtssrr/background.png"/></h1>
<h2 align="center">Simple implementation of ForceTouch on Android</h1>


<span class="badge-paypal"><a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=LY7EX8WMWPWV6" title="Donate to this project using Paypal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" alt="PayPal donate button" /></a></span>
[![Twitter](https://img.shields.io/badge/Twitter-@LacorteMichele-blue.svg?style=flat)](https://twitter.com/LacorteMichele)

[![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat)](https://android-arsenal.com/api?level=14)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ForceTouch-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4560)

##WHAT IS FORCE TOUCH?

You know that Android already detects different screen pressure from API 5? (Eclair, Android 2.0)

Well, I wanted to enlighten you about it, I created a library for Android that takes advantage of this property to implement the Force Touch (or 3D Touch), I have also created a dedicated app to give the opportunity for developers and not, try this library, the app is on the [Play Store](https://play.google.com/store/apps/details?id=it.michelelacorte.exampleforcetouch).

This library can also be used on the launcher and by doing so you can implement ForceTouch on icons of the applications, not using the Long Press (shortcut) introduced in android 7.1. I set myself a custom launcher to test the touch force and are currently in the testing phase, just ready you will see everything on GitHub.

##EXAMPLE

####Force Touch is on Google Play!!!

<a href="https://play.google.com/store/apps/details?id=it.michelelacorte.exampleforcetouch">
<img alt="Get it on Google Play" src="https://s32.postimg.org/50h5qj4lx/google_play_badge.png" />
</a>


![alt tag](https://s14.postimg.org/5s3scqf9t/screen.png)

##USAGE

Add this to `build.gradle`

```groovy
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

Than add this dependencies

```groovy
compile 'com.github.michelelacorte:ForceTouch:1.0.1'
```

```groovy
final ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, 0.27f, true, true, new Callback() {
                        @Override
                        public void onForceTouch() {
                            //functionToInvokeOnForceTouch();
                        }

                        @Override
                        public void onNormalTouch() { 
                            //functionToInvokeOnNormalTouch(); 
                        }
                    });
```

Than you can use Listener with setOnTouchListener method of View

```groovy
view.setOnTouchListener(forceTouchListener);
```

##SYSTEM REQUIREMENT

Android API 14+

##STATUS

![project maintained](https://img.shields.io/badge/Project-Maintained-green.svg)

##CHANGELOG

**v1.0.1**
- Added `isProgressive` boolean to detect progressive pressure! (Default false).
- Added `isVibrate` boolean to set vibration.
- Added method `onNormalTouch` to `Callback` interface, the method is invoked when pressure is low (not overtake pressureLimit).
- Interface `ForceTouchExecution` renamed with `Callback`.
- Improved example App.

**v1.0.0**
- Support API 14+ (API 25 Compatible)
- Added class `ForceTouchListener` with constructor to create Force Touch witch custom param.
- Added interface `ForceTouchExecution` with method `onForceTouch` for define custom action onForceTouch detected.
- Added example App.

##CREDITS

Author: Michele Lacorte (micky1995g@gmail.com)

##CONTRIBUTING

If you want to contribute to the project fork it and open [Pull Request](https://github.com/michelelacorte/ForceTouch/pulls), or contact me by e-mail.

Each proposal will be accepted!

##LICENSE

```
Copyright 2016 Michele Lacorte

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

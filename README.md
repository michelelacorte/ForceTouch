# ForceTouch (Coming Soon!)
<h1 align="center"><img src="https://s13.postimg.org/rodwtssrr/background.png"/></h1>
<h2 align="center">Simple implementation of ForceTouch on Android</h1>


<span class="badge-paypal"><a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&amp;hosted_button_id=LY7EX8WMWPWV6" title="Donate to this project using Paypal"><img src="https://img.shields.io/badge/paypal-donate-yellow.svg" alt="PayPal donate button" /></a></span>
[![Twitter](https://img.shields.io/badge/Twitter-@LacorteMichele-blue.svg?style=flat)](https://twitter.com/LacorteMichele)

[![API](https://img.shields.io/badge/API-14%2B-yellow.svg?style=flat)](https://android-arsenal.com/api?level=14)

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)


##EXAMPLE

####Force Touch is on Google Play (Coming Soon)!!!

![alt tag](https://s14.postimg.org/5973qqtcx/screen.png)

##USAGE

```groovy
final ForceTouchListener forceTouchListener = new ForceTouchListener(getApplicationContext(), 70, 0.27f, new ForceTouchExecution() {
            @Override
            public void onForceTouch() {
                //functionToInvokeOnForceTouch();
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

**v1.0.0 (Coming Soon)**
- Support API 14+ (API 25 Compatible)
- Added class `ForceTouchListener` with constructor to create Force Touch witch custom param
- Added interface `ForceTouchExecution` with method `onForceTouch` for define custom action onForceTouch detected.

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

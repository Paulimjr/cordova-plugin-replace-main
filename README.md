# cordova-plugin-replace-main

Cordova Plugin to replace the MainActivity to another own Custom Activity on Cordova Application

## Installation on OutSystems Platform

    cordova plugin add https://github.com/Paulimjr/cordova-plugin-replace-main.git
---


# API
Methods available:

* **loadValues**(attributes: JSON, success: function, error: function)
    * This method is used to fetch all data saved on SharedPreferences, it follows example below:
    ```javascript
            cordova.plugins.ReplaceMainPlugin.loadValues(
                 {
                    attributes : [
                        {key:"PAYMENT_CODE", type: "int"},
                        {key:"PAYMENT_DESCRIPTION", type: "string"}
                   ]
                },
               function success(data){console.log(data);},
               function error (data){console.log(data);}
            );
    ```


**IMPORTANT:**  If you change the name of OtherMainActivity.java class OR target-dir declared in config.xml, REMEMBER: Go to 'updateMainActivity.js' into scripts folder and change the variable value -> updateMainActivity with new values example: updateMainActivity = "package.ClassName"

  - In additional, *inputExtras* attribute the types accepted is: "bool", "long", "float", "int" and "string".



var fs = require('fs'), path = require('path');

module.exports = function(context) {
    
   var platformRoot = path.join(context.opts.projectRoot, 'platforms/android');
   var manifestFile = path.join(platformRoot, 'app/src/main/AndroidManifest.xml');

   if (fs.existsSync(manifestFile)) {
 
     fs.readFile(manifestFile, 'utf8', function (err,data) {
       if (err) {
         throw new Error('Unable to find AndroidManifest.xml: ' + err);
       }
       
       // This activity needs to be declared in config.xml
       var updateMainActivity = 'com.outsystems.experts.OtherMainActivity';
 
       if (data.indexOf(updateMainActivity) == -1) {
 
         var oldResult = data.replace(/<activity/, '<activity android:name=.MainActivity', "");
         // remove old android:name
         fs.writeFile(manifestFile, oldResult, 'utf8', function (err) {
           if (err) throw new Error('Unable to write in Activity into AndroidManifest.xml: ' + err);
         })

         var result = oldResult.replace(/<activity/, '<activity android:name="' + updateMainActivity + '"');
         // add new  android:name
         fs.writeFile(manifestFile, result, 'utf8', function (err) {
            if (err) throw new Error('Unable to write in Activity into AndroidManifest.xml: ' + err);
          })
       }
     });
   }

};
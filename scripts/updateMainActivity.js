var fs = require('fs'), path = require('path');
module.exports = function(context) {
    
   var platformRoot = path.join(context.opts.projectRoot, 'platforms/android');
   var manifestFile = path.join(platformRoot, 'app/src/main/AndroidManifest.xml');

    // This activity needs to be declared in config.xml
    var updateMainActivity = 'outsystems.experts.OtherMainActivity';

   if (fs.existsSync(manifestFile)) {
 
     fs.readFile(manifestFile, 'utf8', function (err,data) {
      
      if (err) {
         throw new Error('Unable to find AndroidManifest.xml: ' + err);
       }
      
       if (data.indexOf(updateMainActivity) == -1) {
         
        var resultManifest = data.replace('android:name="MainActivity"', 'android:name="' + updateMainActivity + '"');
        
        if (resultManifest.indexOf(updateMainActivity) == -1) {
           resultManifest = resultManifest.replace('android:name=".MainActivity"', 'android:name="' + updateMainActivity + '"');
        }
        
         fs.writeFile(manifestFile, resultManifest, 'utf8', function (err) {
           if (err) throw new Error('Unable to write in Activity into AndroidManifest.xml: ' + err);
         })
       }

     });
   }
};
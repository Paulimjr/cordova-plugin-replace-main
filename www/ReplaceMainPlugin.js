var exec = require('cordova/exec');
/**
 *  This method is used to fetch all data saved on SharedPreferences
 * 
 *  @param {JSON} attributes - The options which wil be used to fetch all informations
 *      Example: 
 *      {
 *          attributes : [
                {key:"PAYMENT_CODE", type: "int"},
                {key:"PAYMENT_DESCRIPTION", type: "string"}
            ]
 *       }
 * @param {Function} success - The callback which will be called when switch to settings is successful.
 * @param {Function} error - The callback which will be called when switch to settings encounters an error.
 */
exports.loadValues = function (attributes, success, error) {
    exec(success, error, 'ReplaceMainPlugin', 'loadValues', [attributes]);
};
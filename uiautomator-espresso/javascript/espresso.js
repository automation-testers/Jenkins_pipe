const request = require('request')

// const username = 'kobiton.username'
// const apiKey = 'dd21c1-6124-5223-9b8a-51e01b41263e'

const username = 'gyanadeeps';
const apiKey = '15a9ea3f-38fb-450c-9706-08a72ed71950';

const encodeAuth = `Basic ${Buffer.from(`${username}:${apiKey}`).toString('base64')}`



const configuration = { 
  sessionName:        'Espresso automation test session',
  sessionDescription: 'Espresso testing example', 
  noReset:            true,
  fullReset:          false,     
  deviceName:         'Galaxy S20+ 5G (Payload Enabled)',
  // The tag is used for finding devices and the user can input only one tag. 
  // For example, the data value will be inputted: tagName="TagName1"
  tagName:            '',
  platformVersion:    '13',  
  // The given team is used for finding devices and the created session will be visible for all members within the team.
  groupId:            12170, // Group: AutomationDocs
  deviceGroup:        'ORGANIZATION',
  app:                'https://kobiton-devvn.s3-ap-southeast-1.amazonaws.com/apps-test/uiautomator-espresso/espresso-app.apk',
  testRunner:         'https://kobiton-devvn.s3-ap-southeast-1.amazonaws.com/apps-test/uiautomator-espresso/esspresso-test-runner.apk', 
  continueOnFailure:   true,
  testFramework:      'UIAUTOMATOR',
  sessionTimeout:      1,

  // If the "tests" is blank, all tests in testRunner will be run

  // To specific some test cases: let's say we have 2 classes Foo & Bar in
  // packages com.abc.xyz.A & com.abc.xyz.B respectively. To run method Test1
  // in class Foo and all tests in class Bar, the input will be:
  //      tests = ["com.abc.xyz.A.Foo#Test1", "com.abc.xyz.B.Bar]

  // To specific package com.abc.xyz.A, the input will be:
  //      tests = ["-e package com.abc.xyz.A"]   
  tests:              []
}

// Access https://github.com/kobiton/samples/tree/master/uiautomator-espresso/javascript to learn more about configuration of NodeJS language.



// Access https://github.com/kobiton/samples/tree/master/uiautomator-espresso/javascript to learn more about configuration of NodeJS language.


  
  // Access https://github.com/kobiton/samples/tree/master/uiautomator-espresso/javascript to learn more about configuration of NodeJS language.
  
  


const headers = {
  'Content-Type':'application/json',
  'Authorization': encodeAuth,
  'Accept':'application/json'
}
const body = {
  configuration
}

request({
  url: 'https://api.kobiton.com/hub/session',
  json: true,
  method: 'POST',
  body,
  headers
}, function (err, response, body) {
  if (err) return console.error('Error:', err)
  console.log('Response body:', body)
})

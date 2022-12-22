import 'babel-polyfill'
import 'colors'
import wd from 'wd'
import {
  assert
} from 'chai'

const username = process.env.KOBITON_USERNAME
const apiKey = process.env.KOBITON_API_KEY

const deviceName = process.env.KOBITON_DEVICE_NAME || 'Galaxy*'
const deviceOrientation = process.env.KOBITON_SESSION_DEVICE_ORIENTATION || 'portrait'
const captureScreenshots = Boolean(process.env.KOBITON_SESSION_CAPTURE_SCREENSHOTS) || true
const deviceGroup = process.env.KOBITON_SESSION_DEVICE_GROUP || 'KOBITON'
const app = process.env.KOBITON_SESSION_APPLICATION_URL || 'https://appium.github.io/appium/assets/ApiDemos-debug.apk'
const platformName = process.env.KOBITON_DEVICE_PLATFORM_NAME || 'Android'
const groupId = Number(process.env.KOBITON_ORGANIZATION_GROUP_ID)

const kobitonServerConfig = {
  protocol: 'http:',
  host: 'api.kobiton.com',
  auth: `${username}:${apiKey}`
}

// const desiredCaps = {
//   sessionName: 'Automation Test Session',
//   sessionDescription: 'This is an example of automation testing of an Android application',
//   deviceOrientation: deviceOrientation,
//   captureScreenshots: captureScreenshots,
//   deviceName: deviceName,
//   deviceGroup: deviceGroup,
//   platformName: platformName,
//   app: app
// }


var webdriverKobitonServerConfig = {
  protocol: 'https',
  //host: 'api.kobiton.com',
  //auth: 'gyanadeeps:15a9ea3f-38fb-450c-9706-08a72ed71950'
}

var desiredCaps = {
  sessionName:        'Automation test session',
  sessionDescription: '',
  deviceOrientation:  'portrait',
  noReset:            true,
  fullReset:          false,
  captureScreenshots: true,
  // The maximum size of application is 4000MB
  // By default, HTTP requests from testing library are expired
  // in 2 minutes while the app copying and installation may
  // take up-to 30 minutes. Therefore, you need to extend the HTTP
  // request timeout duration in your testing library so that
  // it doesn't interrupt while the device is being initialized.
  app:                'https://appium.github.io/appium/assets/ApiDemos-debug.apk',
  
  // The given team is used for finding devices and the created session will be visible for all members within the team.
  groupId:            12170, // Group: AutomationDocs
  deviceGroup:        'ORGANIZATION',
  // For deviceName, platformVersion Kobiton supports wildcard
  // character *, with 3 formats: *text, text* and *text*
  // If there is no *, Kobiton will match the exact text provided
  deviceName:         'Galaxy S20+ 5G (Payload Enabled)',
  // The tag is used for finding devices and the user can input only one tag. 
  // For example, the data value will be inputted: tagName="TagName1"
  tagName:            '',
  platformVersion:    '13',
  platformName:       'Android'
} 


let driver

if (groupId) {
  desiredCaps.groupId = groupId
}

if (!username || !apiKey) {
  console.log('Error: Environment variables KOBITON_USERNAME and KOBITON_API_KEY are required to execute script')
  process.exit(1)
}

describe('Android App sample', () => {


  before(async () => {

    driver = wd.promiseChainRemote(kobitonServerConfig)

    driver.on('status', (info) => {
      console.log(info.cyan)
    })
    driver.on('command', (meth, path, data) => {
      console.log(' > ' + meth.yellow, path.grey, data || '')
    })
    driver.on('http', (meth, path, data) => {
      console.log(' > ' + meth.magenta, path, (data || '').grey)
    })

    try {
      await driver.init(desiredCaps)
    } catch (err) {
      if (err.data) {
        console.error(`init driver: ${err.data}`)
      }
      throw err
    }
  })

  it('should show the app label', async () => {
       
        await driver.elementByClassName("android.widget.Button")
        .text().then(function (text) {
        console.log(text)
        assert.equal(text, 'Cancel')
        
      }) 
  })

       
  after(async () => {
    if (driver != null) {
      try {
        await driver.quit()
      } catch (err) {
        console.error(`quit driver: ${err}`)
      }
    }
  })
})

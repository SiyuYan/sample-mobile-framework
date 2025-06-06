# Wallet Creation QA Assignment

## Manual Test Cases - Create wallet
### Functional test cases
    Flow: Wallet creation -> Create passcode - > Confirm passcode -> Enable notifications -> Wallet ready screen - > Home page - > My wallets page - > Create new wallet

Case 1: (Verify Wallet Creation Entry Point)
Given user downloads the app freshly
When user opens application
Then user should see the "create new wallet" button above "I already have a wallet" button

Case 2:(Wallet passcode setup)
Given user downloads the app and opens it
When user click "create new wallet" button
Then user should see the "create passcode" screen
Then user should see "Enter your passcode. Be sure to remember it so you can unlock your wallet" hint text under input field
When user inputs a passcode with numbers(Eg. 123456, 111111, 121212)
Then each digit button (0-9) on the numeric keypad should be clickable and display marked during input
When user click back button of devices
Then user go back to the "create new wallet" screen again and entered passcode data is cleared

Case 3:(Wallet passcode setup)
Given user at the create passcode screen
When user inputs a passcode with 6 numbers
Then user should see the "confirm passcode" screen
Then user should see "Re-enter your passcode. Be sure to remember it so you can unlock your wallet" hint text under input field

Case 4:(Back button behavior)
Given user at confirm passcode screen
When user click back button
Then user should back to "create wallet" screen instead of "create passcode" screen(Not save passcode)

Case 5:(Wallet passcode setup)
Given user at the "confirm passcode" screen
When user not input number
Then user can not continue create wallet

When user input number less than 6 number
Then user can not continue create wallet

When user input number and delete some inputs
Then user can re-enter passcode

When user inputs the diff 6 numbers passcode with previous screen
Then user will see the "create passcode" screen again
Then user should see the "Those passcodes did not match" hint text under input field

Case 6:(Wallet passcode setup)
Given user at the "confirm passcode" screen
When user inputs the same 6 numbers passcode with previous screen
Then user should enable notifications screen for first time user 

Case 7:(notification setup)
Given user at the notification screen
When user click back button
Then user should back to the "create passcode" screen again
When user at the notification screen and display "enable notifications" button or "skip button
When select "enable notifications" button
Then android device should show the "Allow notifications" popup
When user click "Allow" button
Then user should see the "wallet ready" screen
When user at the notification screen and select "skip" button
Then user should see the "wallet ready" screen 

Case 8:(wallet ready screen after creat wallet success)
Given user at the "wallet ready" screen
Then user should see the "Brilliant, Your wallet is ready!" text
And user should see the Buy crypto button and Deposit crypto button and Skip for now button
And all buttons should be clickable to each page
When user at Home page
Then user should see the "Main Wallet 1" as default create name display with $0.00 balance

Case 9:(create wallet in my wallet page)
Given user at my wallets page
When user clicks "Add wallet" button
Then user should see the "create new wallet" button above "Add existing wallet" button
Given user click "create new wallet" button
Then user should see secret phase selection create and swift selection create

Case 10:(create wallet with secret phase method)
Given user at create new wallet screen (with one main wallet already)
When user clicks "create secret phase" button
Then user go to the "wallet ready" screen with "Brilliant, Your wallet is ready!" text
When user at Home page
Then user should see the "Main Wallet 2" as default create name display with $0.00 balance
When user at Wallets screen 
And 2 wallets in my wallets page display at Multi-coin wallets section
Then first wallet name should be "Main Wallet 1" 
And second wallet name should be "Main Wallet 2" and second one wallet mark icon with default

Case 11:(create wallet with swift method)
Given user at create new wallet screen (with one main wallet already)
When user clicks "create swift selection" button
Then security tips display
When user select all security tips and click Continue button
Then Quick quiz question page display
When user answers question incorrectly
Then user must change answer and click Continue button
When user answers question correctly
Then user go to "Set wallet name" screen

Case 12:(set wallet name with swift method)
Given user at "Set wallet name" screen
When user inputs valid wallet name less than 4 character or more than 24 characters
Then the Done button should be disabled
When user inputs valid wallet name 4-24 characters. Eg. YanSiyu(special characters test as well based on requirement)
Then the Done button should be enabled
When user clicks Done button
Then Google passcode manager popup should display

When click Continue button and input incorrect PIN
Then can not process with Google passcode manager

When Google passcode manager popup and click continue button and input correct PIN
Then user go to the "wallet ready" screen with "Brilliant, Your wallet is ready!" text
When user at Home page
Then user should see the "YanSiyu" as default create name display with $0.00 balance
When user at Wallets screen
Then 3 wallets in my wallets page display and third one wallet "YanSiyu" have mark icon with default
And "YanSiyu" wallet display at new Swift wallets sections

Case 13:(turnoff network create wallet)
Given user turnoff network
When Google passcode manager popup and click continue button and input correct PIN when
Then display please try again and back to set wallet name screen

Case 14:(End to end flow)
Given user at the "create passcode" screen
When user inputs a passcode with 6 numbers
And user inputs the same 6 numbers passcode at "confirm passcode" screen
And user enables notifications
Then user at the "wallet ready" screen
When user clicks "Skip for now" button
Then user should see the "Home" page
When user clicks "Main Wallet 1" button at top
Then user should see the "My wallets" page with one wallet created


Edge cases:
1. Test disturb process by click home button of Android or disturb by other app and re-open app again: should not impact the process
2. Test back button on each screen: should go back to previous screen
3. Test the max number of wallets can created by one device(depending on requirement max limit)
4. Multiple languages: Change phone default language to different languages and check if the app supports it.
5. Uninstall and reinstall the app and check if wallet can be restored(depends on requirement)
6. Test turnoff network and all function works(except Google passcode manager interaction Case 13) 
7. Test rotation of the screen during the flow of all pages
8. Test diff Android devices with different screen sizes and resolutions
API Test：
1. Test double click on create wallet button should not create wallet again(same params and traceId)
2. Stress API test with create wallet API check if can handle 1000 requests per second(Based on requirement)

Found potential issues:
1. Quick double click on create wallet button, 
   should not open two input passcode screen. 

## Automation test
env setup: `mvn install`
run test: `mvn clean test`
generate Allure report: `mvn allure:report`
To view the test report, open the Allure: report at target/site/allure-maven-plugin/index.html, the failed cases screenshot is at tearDown stage attached

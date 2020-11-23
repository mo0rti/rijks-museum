<h1 align="center"> Rijks Museum Sample Project </h1>

This project demonstrated a list of information related to an artist with the help of Rijks Museum apis.
The implementation are based on MVVM with Flow and coroutines to implement unit testing for multiple flow emissions.

Structure
---------
- MainActivity: Host Activity
- ArtCollectionFragment: To show a list of Art objects
- ArtObjectFragment: To show details of an Art Object
- di: Contain factory and provider helper classes
- data: Contains datasources (Cache and Network) and repostiory for ArtObject.
- service: Contains classes to work with Room and Retrofit
- domain: Contains domain classes and common classes to work with Flow and object mapping
- utils: Contains logging methods and application wide constants such as API key and refresh interval

Libraries
---------
- Mockito : For mock environment setup
- ViewModel: To implement MVVM
- Arch-core-testing: For LiveData synchronous event testing
- Kotlinx-coroutines-test: For coroutines synchronous testing setup
- KTX-libs: For kotlin support and extensions
- Retrofit: For Api calls
- Room: For data layer
- Mockito-Kotlin: For some helper functions to work with Mockito in Kotlin.
- Espresso: for UI test
- Hilt: For DI to reduce the boilerplate codes

Explanation
------------
This application is working based on an api key, the api key located in the `utils/Constants.kt` file.
The information about the api can be found at `http://rijksmuseum.github.io`
Each part of the application seperated into its own directory based on the its domain.
In repostiory and datasource directories, there are 3 files:
- Interface of the repository or data source
- Implementation
- Mock Implementation


The levels of dependent classes are as follows:
 - UI
   - Repository
      - Data sources (Network, Cache)
         - Services (network, Cache)
           - Room
           - Retrofit


Tests
------------
Tests are divided into two parts:
1. androidTest: which contains the UI tests using espresso. There is also a fake module which injects
the mock implementations (mentioned above).
A class also added to the project to fix an issue on fragment isolation tests (`HiltExt.kt`)
2. test: which contains the functionalty test cases for ArtObjectRepostiory.

Screen shots
------------

#### List

<img src="https://raw.githubusercontent.com/mo0rti/rijksmuseum/master/screenshots/list.png" width="400" aspectRatio="1/2">

#### Details
<img src="https://raw.githubusercontent.com/mo0rti/rijksmuseum/master/screenshots/detail.png" width="400" aspectRatio="1/2">
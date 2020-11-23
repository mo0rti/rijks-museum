<h1 align="center"> Rijks Museum Sample Project </h1>

Flow can emit the data asynchronously(like streams) which can be transformed and collected as Live Data to implement MVVM.
This project demonstrated the implementation of MVVM with Flow and coroutines to implement unit testing for multiple flow emissions.

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
The information about the api can be found at http://rijksmuseum.github.io
Each part of the application seperated into its own directory based on the its domain.
In repostiory and datasource directories, there are 3 files:
- Interface of the repository or data source
- Implementation
- Mock Implementation


The level of dependent classes are as follows:
 - UI
   - Repository
      - Data sources (Network, Cache)
         - Services (network, Cache)
           - Room
           - Retrofit



Tests
------------
Tests are divided into two parts:
1. androidTest: which contains the UI test using espresso. There is also a Fake module which injects
the mock implementations (mentioned above), to fix an issue on fragment isolation tests a class has been added to the project
`HiltExt.kt`
2. test: which contains the functionalty test cases for ArtObjectRepostiory.

Screen shots
------------

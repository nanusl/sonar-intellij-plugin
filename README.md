<p align="center">
<img width="100" src="https://note.yujunyang.com/static/2021/8/057b00d5f6f8f467f89293e3dfa246b6.png">
</p>

<h1 align="center">Sonar Intellij Plugin</h1>

<p>
<img src="https://img.shields.io/jetbrains/plugin/v/17542"/>
<img src="https://img.shields.io/github/license/lowkeyfish/sonar-intellij-plugin"/>
</p>

English | [简体中文](README.zh-CN.md)

Sonar Intellij Plugin is an Intellij IDEA plugin, you can use Sonar Intellij Plugin in Intellij IDEA to perform Sonar code analysis on Java projects, view code analysis reports, and prompt problem codes. 


## Install

Sonar Intellij Plugin has been released to the JetBrains plugin marketplace, the latest version is [0.1.1](https://plugins.jetbrains.com/plugin/17542-sonaranalyzer). 

Go to `Settings` / `Plugins` / `Marketplace` (under macOS is`Preferences` / `Plugins` / `Marketplace`), search plugin by keyword `SonarAnalyzer` then install `SonarAnalyzer` plugin:

![sonar-intellij-plugin-install](https://note.yujunyang.com/static/2021/8/6d863e1d7b882d586f695b149c83671c.png)

After the plugin is installed successfully, restart the IDE to take effect. 

## Usage

Sonar Intellij Plugin uses [SonarScanner](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/) to perform code analysis. SonarScanner needs to connect to the SonarQube server. Therefore, you need to make some necessary settings before using Sonar Intellij Plugin, and then perform code analysis on the project.

### Settings

The plugin can be set at the IDE level and the Project level. Go to `Settings` / `Tools` / `SonarAnalyzer` (under macOS is `Preferences` / `Tools` / `SonarAnalyzer`) to set the plugin.

IDE level settings include:

* Plugin language switch (currently supports Chinese and English) 
* Manage SonarQube connections (add, update, delete) 
* Manage global SonarScanner properties (add, update, delete) 

Project level settings include:

* Bind SonarQube connection for Project
* Manage the SonarScanner properties used by the current Project (add, update, delete, and control whether to inherit the global SonarScanner properties) 

#### SonarQube Connection

All SonarQube connections can be managed at the IDE level, and SonarQube connections can be added, deleted and updated. 

Each SonarQube connection must provide a name, URL, and Token. Add SonarQube connection: 

![sonar-intellij-plugin-english-add-connection](https://note.yujunyang.com/static/2021/8/220507e0146db0d6bf1ca4d56d62d5fb.png)

In the Project level settings, you can specify the SonarQube connection to be bound for the Project: 

![sonar-intellij-plugin-english-project-settings](https://note.yujunyang.com/static/2021/8/f8bdb764bc28f9b6e20ac866a80db975.png)

If the Project does not specify a SonarQube connection, the first SonarQube connection that has been added is used by default. 


#### SonarScanner Property

When using SonarScanner inside the plugin, reasonable and complete properties have been used as much as possible, but you may still need to set some properties independently. Currently, you can set two properties: `sonar.exclusions` and `sonar.cpd.exclusions`. 

Add property:

![sonar-intellij-plugin-english-add-property](https://note.yujunyang.com/static/2021/8/2d87655831fa07b60caa079b21bb80ec.png)

You can manage properties at the IDE level and the Project level, add the properties required by all projects at the IDE level, and then you can set whether to inherit the properties added at the IDE level in the Project settings, and manage the special properties of the current Project: 

![sonar-intellij-plugin-english-project-settings](https://note.yujunyang.com/static/2021/8/f8bdb764bc28f9b6e20ac866a80db975.png)

### Analysis

#### Start Analysis

After setting the plugin, you can trigger the project code analysis in the following places: 

* The `SonarAnalyzer` menu in the context menu of the content in the Project Tool Window
* The `SonarAnalyzer` menu in the context menu of the code editor 
* The start action button in the SonarAnalyzer Tool Window

After starting the code analysis, the plugin will start the code compilation, and then use SonarScanner to complete the code analysis after the compilation is successful. The entire operation log will be output in the `Log` of the SonarAnalyzer Tool Window: 

![sonar-intellij-plugin-english-log](https://note.yujunyang.com/static/2021/8/1913d1020b5b20c0f82383957d737366.png)

#### Analysis Result

When the code analysis is completed, the complete analysis report will be displayed in the `Report` of the SonarAnalyzer Tool Window, and the problem code line will also be prompted in the editor: 

![sonar-intellij-plugin-english-report](https://note.yujunyang.com/static/2021/8/e832ccc752fcb55577da923e77f3b58b.png)


## Contributing

Feel free to dive in! Open an [issue](https://github.com/lowkeyfish/sonar-intellij-plugin/issues/new) or submit PRs.

## License

GPL-3.0 &copy; Yu Junyang










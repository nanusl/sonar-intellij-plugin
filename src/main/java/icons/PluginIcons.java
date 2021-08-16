package icons;

import javax.swing.Icon;

import com.intellij.icons.AllIcons;
import com.intellij.ui.LayeredIcon;
import com.yujunyang.intellij.plugin.sonar.resources.ResourcesLoader;

public interface PluginIcons {
    Icon LOGO = ResourcesLoader.loadIcon("logo.svg");
    Icon LOGO_13X13 = ResourcesLoader.loadIcon("logo13x13.svg");

    Icon SONAR_LOGO = ResourcesLoader.loadIcon("sonar.svg");
    Icon SONAR_LOGO_13X13 = ResourcesLoader.loadIcon("sonar13x13.svg");
    Icon SONAR_QUBE = ResourcesLoader.loadIcon("SonarQube.svg");


    Icon ANALYZE_PROJECT_FILES_NOT_INCLUDING_TESTS_ICON = LayeredIcon.create(AllIcons.Nodes.Project, AllIcons.Nodes.RunnableMark);

    Icon ANALYZE_EXECUTE = AllIcons.Actions.Execute;
    Icon ANALYZE_SUSPEND = AllIcons.Actions.Suspend;
    Icon PLUGIN_SETTING = AllIcons.General.GearPlain;
    Icon PLUGIN_HELP = AllIcons.Actions.Help;

    Icon SMILE = ResourcesLoader.loadIcon("smile.svg");

    Icon ISSUE = ResourcesLoader.loadIcon("issue.svg");

    Icon BUGS = ResourcesLoader.loadIcon("bugs.svg");
    Icon VULNERABILITY = ResourcesLoader.loadIcon("vulnerability.svg");
    Icon CODE_SMELL = ResourcesLoader.loadIcon("codeSmell.svg");
    Icon BLOCKER = ResourcesLoader.loadIcon("blocker.svg");
    Icon CRITICAL = ResourcesLoader.loadIcon("critical.svg");
    Icon MAJOR = ResourcesLoader.loadIcon("major.svg");
    Icon MINOR = ResourcesLoader.loadIcon("minor.svg");
    Icon INFO = ResourcesLoader.loadIcon("info.svg");
}

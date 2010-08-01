package org.netmelody.osnamer.shared;

public enum ProjectHostName {
    GITHUB("<img src=\"http://github.com/images/modules/header/logov3.png\" alt=\"GitHub\""),
    BITBUCKET("<img src=\"http://bitbucket-assets.s3.amazonaws.com/img/logo_myriad.png\" alt=\"bitbucket\""),
    SOURCEFORGE("<img src=\"http://mirrorbrain.org/static/images/misc/sourceforge-logo.png\" alt=\"sourceforge\""),
    CODEPLEX("<img src=\"http://i1.codeplex.com/Images/v17027/logo-home.png\" alt=\"CodePlex\" style=\"background-color: lightgray;\""),
    LAUNCHPAD("<img src=\"https://launchpad.net/@@/launchpad-logo-and-name-hierarchy.png\" alt=\"Launchpad\"");

    private final String htmlDesc;

    private ProjectHostName(String htmlDesc) {
        this.htmlDesc = htmlDesc;
    }
    
    public String getHtmlDesc() {
        return htmlDesc;
    }
}

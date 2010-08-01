package org.netmelody.osnamer.shared;

public enum ProjectHostName {
    GITHUB("<img src=\"http://github.com/images/modules/header/logov3.png\" alt=\"GitHub\""),
    BITBUCKET("<img src=\"http://bitbucket-assets.s3.amazonaws.com/img/logo_myriad.png\" alt=\"bitbucket\""),
    SOURCEFORGE("<img src=\"http://mirrorbrain.org/static/images/misc/sourceforge-logo.png\" alt=\"sourceforge\""),
    CODEPLEX("<img src=\"http://i1.codeplex.com/Images/v17027/logo-home.png\" alt=\"CodePlex\" style=\"background-color: lightgray;\""),
    LAUNCHPAD("<img src=\"https://launchpad.net/@@/launchpad-logo-and-name-hierarchy.png\" alt=\"Launchpad\""),
    PROJECTKENAI("<img src=\"http://www.techcrunchit.com/wp-content/uploads/2010/02/ProjectKenai.jpg\" alt=\"Project Kenai\""),
    OHLOH("<img src=\"http://upload.wikimedia.org/wikipedia/en/2/2d/Ohloh_logo.png\" alt=\"Ohloh\"");

    private final String htmlDesc;

    private ProjectHostName(String htmlDesc) {
        this.htmlDesc = htmlDesc;
    }
    
    public String getHtmlDesc() {
        return htmlDesc;
    }
}

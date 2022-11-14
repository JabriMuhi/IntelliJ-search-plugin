package com.jabrimuhi.itemsearchplugin.itemsearchplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformCoreDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleTargetSearchJava extends AnAction {
    //plugin only for java :(, mb you can delete the "+java" in url, but same methods and classes in
    // most languages this keys may duplicate
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String encodedTarget;
        Messages.showMessageDialog("Press OK, them you go in browser",
                "Google Target Search",
                Messages.getInformationIcon());


        Editor searchEditor = e.getData(PlatformCoreDataKeys.EDITOR);
        String target = searchEditor.getSelectionModel().getSelectedText();


        try {
            encodedTarget = URLEncoder.encode(target, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        String url = "https://www.google.com/search?q=" + encodedTarget + "+java";
        BrowserUtil.browse(url);
    }

    @Override
    public boolean isDumbAware() {
        //method for start the plugin before indexing project
        return super.isDumbAware();
    }
}

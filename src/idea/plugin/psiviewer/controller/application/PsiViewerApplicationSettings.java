package idea.plugin.psiviewer.controller.application;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.util.xmlb.XmlSerializerUtil;
import idea.plugin.psiviewer.PsiViewerConstants;
import idea.plugin.psiviewer.util.Helpers;
import org.jetbrains.annotations.Nullable;

import static idea.plugin.psiviewer.PsiViewerConstants.DEFAULT_HIGHLIGHT_COLOR;

/**
 * Created by Jon on 10/7/2016.
 */
@State(name = PsiViewerConstants.CONFIGURATION_COMPONENT_NAME, storages = @Storage("other.xml"))
public class PsiViewerApplicationSettings implements PersistentStateComponent<PsiViewerApplicationSettings> {
    public String HIGHLIGHT_COLOR;
    public boolean PLUGIN_ENABLED;

    private final TextAttributes _textAttributes = new TextAttributes();

    public PsiViewerApplicationSettings() {
        HIGHLIGHT_COLOR = DEFAULT_HIGHLIGHT_COLOR;
        PLUGIN_ENABLED = true;
        getTextAttributes().setBackgroundColor(Helpers.parseColor(HIGHLIGHT_COLOR));
    }

    @Nullable
    @Override
    public PsiViewerApplicationSettings getState() {
        return this;
    }

    @Override
    public void loadState(PsiViewerApplicationSettings state) {
        XmlSerializerUtil.copyBean(state, this);

        getTextAttributes().setBackgroundColor(Helpers.parseColor(HIGHLIGHT_COLOR));
    }

    public static PsiViewerApplicationSettings getInstance() {
        return ServiceManager.getService(PsiViewerApplicationSettings.class);
    }

    public TextAttributes getTextAttributes()
    {
        return _textAttributes;
    }
}

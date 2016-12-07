package sqlrunner.resources;

import javax.swing.ImageIcon;

/**
 * Class contains images as static variables.
 * This class is generated by the IconCodeGenerator
 *
 * author: jan
 * created at: Wed Dec 07 16:25:33 CET 2016
 */
public class ApplicationIcons {

	public static final ImageIcon ADMINEDIT_PNG = createIcon("/sqlrunner/resources/adminedit.png");
	public static final ImageIcon ADMINRUN_PNG = createIcon("/sqlrunner/resources/adminrun.png");
	public static final ImageIcon AUSRUECK_GIF = createIcon("/sqlrunner/resources/ausrueck.gif");
	public static final ImageIcon CATALOG_GIF = createIcon("/sqlrunner/resources/catalog.gif");
	public static final ImageIcon COMMENTS_GIF = createIcon("/sqlrunner/resources/comments.gif");
	public static final ImageIcon COMMIT_GIF = createIcon("/sqlrunner/resources/commit.gif");
	public static final ImageIcon COPY_GIF = createIcon("/sqlrunner/resources/copy.gif");
	public static final ImageIcon COPY_PNG = createIcon("/sqlrunner/resources/copy.png");
	public static final ImageIcon CUT_GIF = createIcon("/sqlrunner/resources/cut.gif");
	public static final ImageIcon DATAMODEL_PNG = createIcon("/sqlrunner/resources/datamodel.png");
	public static final ImageIcon DB_GIF = createIcon("/sqlrunner/resources/db.gif");
	public static final ImageIcon DBCLOSE_GIF = createIcon("/sqlrunner/resources/dbclose.gif");
	public static final ImageIcon DBOPEN_GIF = createIcon("/sqlrunner/resources/dbopen.gif");
	public static final ImageIcon DOWN_GIF = createIcon("/sqlrunner/resources/down.gif");
	public static final ImageIcon EINRUECK_GIF = createIcon("/sqlrunner/resources/einrueck.gif");
	public static final ImageIcon EXPLAIN_PNG = createIcon("/sqlrunner/resources/explain.png");
	public static final ImageIcon FIELD_PNG = createIcon("/sqlrunner/resources/field.png");
	public static final ImageIcon FOLDER_GIF = createIcon("/sqlrunner/resources/folder.gif");
	public static final ImageIcon FOLDER_CLOSED_PNG = createIcon("/sqlrunner/resources/folder_closed.png");
	public static final ImageIcon FUNCTION_PNG = createIcon("/sqlrunner/resources/function.png");
	public static final ImageIcon GOTO_GIF = createIcon("/sqlrunner/resources/goto.gif");
	public static final ImageIcon HELP_GIF = createIcon("/sqlrunner/resources/help.gif");
	public static final ImageIcon HIGHLIGHT_PNG = createIcon("/sqlrunner/resources/highlight.png");
	public static final ImageIcon HISTORY_PNG = createIcon("/sqlrunner/resources/history.png");
	public static final ImageIcon INSERT_PNG = createIcon("/sqlrunner/resources/insert.png");
	public static final ImageIcon LOWERCASE_GIF = createIcon("/sqlrunner/resources/lowercase.gif");
	public static final ImageIcon NEW_GIF = createIcon("/sqlrunner/resources/new.gif");
	public static final ImageIcon NEXT_GIF = createIcon("/sqlrunner/resources/next.gif");
	public static final ImageIcon OPEN_GIF = createIcon("/sqlrunner/resources/open.gif");
	public static final ImageIcon PASTE_GIF = createIcon("/sqlrunner/resources/paste.gif");
	public static final ImageIcon PREV_GIF = createIcon("/sqlrunner/resources/prev.gif");
	public static final ImageIcon PROCEDURE_PNG = createIcon("/sqlrunner/resources/procedure.png");
	public static final ImageIcon REDO_GIF = createIcon("/sqlrunner/resources/redo.gif");
	public static final ImageIcon ROLLBACK_GIF = createIcon("/sqlrunner/resources/rollback.gif");
	public static final ImageIcon SAVE_GIF = createIcon("/sqlrunner/resources/save.gif");
	public static final ImageIcon SCHEMA_PNG = createIcon("/sqlrunner/resources/schema.png");
	public static final ImageIcon SCRIPT_GIF = createIcon("/sqlrunner/resources/script.gif");
	public static final ImageIcon SEARCH_GIF = createIcon("/sqlrunner/resources/search.gif");
	public static final ImageIcon SEQUENCE_PNG = createIcon("/sqlrunner/resources/sequence.png");
	public static final ImageIcon START_GIF = createIcon("/sqlrunner/resources/start.gif");
	public static final ImageIcon STOP_GIF = createIcon("/sqlrunner/resources/stop.gif");
	public static final ImageIcon TABLE_PNG = createIcon("/sqlrunner/resources/table.png");
	public static final ImageIcon TABLESEARCH_GIF = createIcon("/sqlrunner/resources/tablesearch.gif");
	public static final ImageIcon TOGGLETABLEORIENTATIONICON_PNG = createIcon("/sqlrunner/resources/toggleTableOrientationIcon.png");
	public static final ImageIcon UNDO_GIF = createIcon("/sqlrunner/resources/undo.gif");
	public static final ImageIcon UP_GIF = createIcon("/sqlrunner/resources/up.gif");
	public static final ImageIcon UP_DOWN_EXCHANGE_PNG = createIcon("/sqlrunner/resources/up_down_exchange.png");
	public static final ImageIcon UPPERCASE_GIF = createIcon("/sqlrunner/resources/uppercase.gif");
	public static final ImageIcon VIEW_PNG = createIcon("/sqlrunner/resources/view.png");

	private static ImageIcon createIcon(String iconName) {
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ApplicationIcons.class.getResource(iconName));
		} catch (Exception e) {
			System.err.println("unable to load icon: " + iconName);
			icon = new ImageIcon();
		}
		return icon;
	}

}
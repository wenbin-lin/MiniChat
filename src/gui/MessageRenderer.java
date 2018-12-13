package gui;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class MessageRenderer implements ListCellRenderer<Object> {

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		return (MessagePanel)value;
	}
}
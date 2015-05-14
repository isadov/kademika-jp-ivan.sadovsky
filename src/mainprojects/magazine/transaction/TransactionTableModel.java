package mainprojects.magazine.transaction;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TransactionTableModel extends AbstractTableModel{

    public List<Transaction> register;

    public TransactionTableModel() {
        register = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return register.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}

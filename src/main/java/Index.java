public class Index {
    private int row;
    private int column;
    
    public Index(int row,int column)
    {
        this.row = row;
        this.column=column;
    }
    
    public int getRow()
    {
       return row;
    }
    public int getColumn()
    {
        return column;
    }
    
    public void setRow(int i)
    {
        this.row=i;
    }
    
    public void setColumn(int j)
    {
        this.column=j;
    }
}


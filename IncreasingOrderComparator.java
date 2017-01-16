import java.util.Comparator;

public class IncreasingOrderComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer x, Integer y)
    {
        if (x < y)
        {
            return -1;
        }
        if (x > y)
        {
            return 1;
        }
        return 0;
    }
}
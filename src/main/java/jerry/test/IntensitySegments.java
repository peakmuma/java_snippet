package jerry.test;

public class IntensitySegments {

    public static void main(String[] args) {
        IntensitySegments segments = new IntensitySegments();
        System.out.println(segments);
        segments.add(10, 20, 1); //添加第一个区间
        System.out.println(segments);
        segments.add(20, 30, 2); //在最右边添加一个区间
        System.out.println(segments);
        segments.add(0, 10, 3); //在最左边添加一个区间
        System.out.println(segments);
        segments.add(5, 25, 1); //add的区间，与最左边和最右边的区间都相交，包含中间的区间
        System.out.println(segments);
        segments.add(12, 18, 1); //add的区间，是中间区间的子区间
        System.out.println(segments);
        segments.set(10,20, 1); //测试set
        System.out.println(segments);
        segments.set(10,25, 1); //测试set，向前进行合并
        System.out.println(segments);
        segments.set(5,15, 1); //测试set，向后进行合并
        System.out.println(segments);
        segments.add(5, 25, 2); //测试add区间，导致合并的情况
        System.out.println(segments);

        segments = new IntensitySegments();
        System.out.println(segments); //测试新建
        segments.add(10, 20, 1); //添加第一个区间
        System.out.println(segments);
        segments.add(10, 15, 1); //add的区间，from已经存在，to不存在的情况
        System.out.println(segments);
        segments.add(13, 15, 1); //add的区间，from不存在，to存在的情况
        System.out.println(segments);
        segments.add(13, 20, 1); //add的区间，from存在，to存在的情况
        System.out.println(segments);
    }


    //使用链表存放区间数据，原因是考虑到add操作和set操作会有较多的区间增加、删除、合并操作，因此采用链表存储
    //链表按照 start 有序排列
    private static class Node {
        int start;  //表示区间的开始节点
        int intensity; //表示区间的intensity值
        Node next;

        Node(int start,int intensity) {
            this.start = start;
            this.intensity = intensity;
        }
    }

    private Node head;

    public IntensitySegments() {
        head = new Node(0,0);
    }

    //整体思路是先将 from节点 和 to节点添加到链表中，如果已经存在了就不添加
    //然后将 [from, to) 之间的节点的 intensity 都添加 amount
    //由于有可能相邻区间 intensity 相同，最后进行区间的合并
    public void add(int from, int to, int amount) {
        Node node = head;
        int toNodeValue = findToNodeValue(to); //在添加节点之间，先确定toNode节点的值
        //找到from节点 或 将from节点添加到链表中
        while (node == head || node.start < from) {
            if (node.next == null || node.next.start > from) {
                //如果当前节点是结尾 或者 下一个节点的值大于 from, 新建node节点，退出循环
                Node fromNode = new Node(from, node.intensity + amount);
                fromNode.next = node.next;
                node.next = fromNode;
                node = fromNode;
                break;
            } else if (node.next.start == from) {
                //如果下一个节点等于from，修改intensity值，退出循环
                node.next.intensity = node.next.intensity + amount;
                node = node.next;
                break;
            } else {
                //此种情况是下一个节点小于from，继续遍历
                node = node.next;
            }
        }
        //此时 node 指向 fromNode 节点
        //继续遍历链表，直到找到to节点 或 将to节点添加到链表中，将 from 和 to 之间的区间都增加 amount
        while (node.start < to) {
            if (node.next == null || node.next.start > to) {
                //如果当前节点是结尾, 或者下一节点大于to， 新增node节点，退出循环
                Node toNode = new Node(to, toNodeValue);
                toNode.next = node.next;
                node.next = toNode;
                break;
            } else if (node.next.start == to) {
                //如果下一个节点等于to，退出循环
                break;
            } else {
                //此种情况是下一个节点小于to，将区间的值增加 amount之后 继续遍历
                node.next.intensity = node.next.intensity + amount;
                node = node.next;
            }
        }
        //修改完amount之后，会有相邻区间值相同的情况，进行区间的合并
        mergeNode();
    }

    //整体思路是先将 from节点 和 to节点添加到链表中，如果已经存在了就不添加
    //然后将 [from, to) 之间的节点的intensity 都设置为amount
    //由于有可能相邻区间 intensity 相同，最后进行区间的合并
    public void set(int from, int to, int amount) {
        Node node = head;
        int toNodeValue = findToNodeValue(to); //在添加节点之间，先确定toNode节点的值
        //找到from节点 或 将from节点添加到链表中
        while (node == head || node.start < from) {
            if (node.next == null || node.next.start > from) {
                //如果当前节点是结尾 或者 下一个节点的值大于 from, 新建node节点，退出循环
                Node fromNode = new Node(from, amount);
                fromNode.next = node.next;
                node.next = fromNode;
                node = fromNode;
                break;
            } else if (node.next.start == from) {
                //如果下一个节点等于from，修改intensity值，退出循环
                node.next.intensity = amount;
                node = node.next;
                break;
            } else {
                //此种情况是下一个节点小于from，继续遍历
                node = node.next;
            }
        }
        //此时 node 指向 fromNode 节点
        //继续遍历链表，直到找到to节点或将to节点添加到链表中，将 from 和 to 之间的区间都设置 amount
        while (node.start < to) {
            if (node.next == null || node.next.start > to) {
                //如果当前节点是结尾，或者下一节点大于to, 新增node节点，退出循环
                Node toNode = new Node(to, toNodeValue);
                toNode.next = node.next;
                node.next = toNode;
                break;
            } else if (node.next.start == to) {
                //如果下一个节点等于to，退出循环
                break;
            } else {
                //此种情况是下一个节点小于to，将区间的值设置 amount之后 继续遍历
                node.next.intensity = amount;
                node = node.next;
            }
        }
        //修改完amount之后，会有相邻区间值相同的情况，进行区间的合并
        mergeNode();
    }



    private void mergeNode() {
        Node node = head.next;
        while (node != null) {
            while (node.next != null && node.intensity == node.next.intensity) {
                node.next = node.next.next;
            }
            node = node.next;
        }
    }

    private int findToNodeValue(int to) {
        int toNodeValue = 0;
        Node node = head.next;
        while (node != null && node.start < to) {
            toNodeValue = node.intensity;
            node = node.next;
        }
        return toNodeValue;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node node = head.next;
        while (node != null) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append("[").append(node.start).append(",").append(node.intensity).append("]");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

}


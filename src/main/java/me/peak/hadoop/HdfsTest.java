package me.peak.hadoop;

//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataOutputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.parquet.column.page.PageReadStore;
//import org.apache.parquet.example.data.Group;
//import org.apache.parquet.example.data.simple.SimpleGroupFactory;
//import org.apache.parquet.example.data.simple.convert.GroupRecordConverter;
//import org.apache.parquet.format.converter.ParquetMetadataConverter;
//import org.apache.parquet.hadoop.ParquetFileReader;
//import org.apache.parquet.hadoop.ParquetWriter;
//import org.apache.parquet.hadoop.example.ExampleParquetWriter;
//import org.apache.parquet.hadoop.metadata.ParquetMetadata;
//import org.apache.parquet.io.ColumnIOFactory;
//import org.apache.parquet.io.MessageColumnIO;
//import org.apache.parquet.io.RecordReader;
//import org.apache.parquet.schema.*;

import java.io.IOException;
import java.net.URI;


public class HdfsTest {

//    private static final String HDFS_PATH = "hdfs://localhost:9000";
//    private static final String HDFS_USER = "gaolei";
//    public static final MessageType FILE_SCHEMA = Types.buildMessage()
//            .required(PrimitiveType.PrimitiveTypeName.INT32).named("id")
//            .required(PrimitiveType.PrimitiveTypeName.BINARY).as(OriginalType.UTF8).named("name")
//            .named("test");
//
//    public static void main(String[] args) {
//        try {
//            testParquetWrite();
////            testParquetRead();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testParquetWrite() throws IOException {
//        Configuration configuration = new Configuration();
//        configuration.set("fs.defaultFS", HDFS_PATH);
//        Path path = new Path("/parquet/parquet_test_1.parquet");
//        SimpleGroupFactory f = new SimpleGroupFactory(FILE_SCHEMA);
//        ParquetWriter<Group> writer = ExampleParquetWriter.builder(path).withConf(configuration).withType(FILE_SCHEMA).build();
//        for (int i = 0; i < 100; i++) {
//            Group group = f.newGroup();
//            group.add("id", i);
//            group.add("name", "name" + i);
//            writer.write(group);
//        }
//        writer.close();
//    }
//
//    public static void testParquetRead() throws Exception{
//        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", HDFS_PATH);
//        Path path = new Path("/parquet/parquet_test_2.parquet");
//
//        ParquetMetadata readFooter = ParquetFileReader.readFooter(conf, path, ParquetMetadataConverter.NO_FILTER);
//        MessageType schema = readFooter.getFileMetaData().getSchema();
//        ParquetFileReader r = new ParquetFileReader(conf, path, readFooter);
//
//        PageReadStore pages = null;
//        while (null != (pages = r.readNextRowGroup())) {
//            final long rows = pages.getRowCount();
//            System.out.println("Number of rows: " + rows);
//            final MessageColumnIO columnIO = new ColumnIOFactory().getColumnIO(schema);
//            final RecordReader recordReader = columnIO.getRecordReader(pages, new GroupRecordConverter(schema));
//            for (int i = 0; i < rows; i++) {
//                Group g = (Group) recordReader.read();
//                printGroup(g);
//            }
//        }
//        r.close();
//    }
//
//    private static void printGroup(Group g) {
//        int fieldCount = g.getType().getFieldCount();
//        for (int field = 0; field < fieldCount; field++) {
//            int valueCount = g.getFieldRepetitionCount(field);
//
//            Type fieldType = g.getType().getType(field);
//            String fieldName = fieldType.getName();
//
//            for (int index = 0; index < valueCount; index++) {
//                if (fieldType.isPrimitive()) {
//                    System.out.println(fieldName + " " + g.getValueToString(field, index));
//                }
//            }
//        }
//        System.out.println("");
//    }
//
//    public static void testHdfsWrite() throws Exception {
//        FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), new Configuration(), HDFS_USER);
//        fileSystem.mkdirs(new Path("/client/test/"));
//        FSDataOutputStream out = fileSystem.create(new Path("/client/test/a.txt"), true, 4096);
//        out.write("hello hdfs".getBytes());
//        out.flush();
//        out.close();
//    }



}

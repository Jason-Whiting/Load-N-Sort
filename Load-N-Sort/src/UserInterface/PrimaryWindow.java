package UserInterface;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import java.io.*;
import java.util.logging.*;
import javafx.stage.*;
import OtherMethods.*;
import SortingMethods.*;

public class PrimaryWindow extends Application
{
    private boolean isDouble;
    private boolean isMatrix;
    public static TextArea unsorted;
    public static TextArea sorted;
    private int[] intArray;
    private double[] doubleArray;
    private int[][] intMatrix;
    private double[][] doubleMatrix;
    private String[] stringArray;
    
    public PrimaryWindow() {
        this.isDouble = false;
        this.isMatrix = false;
    }
    
    public static void main(final String[] args) {
        launch(args);
    }
    
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Analysis of Algorithms: Load-N-Sort 3.0");
        stage.setWidth(960.0);
        stage.setHeight(720.0);
        stage.setResizable(false);
        final BorderPane root = new BorderPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        final GridPane options = new GridPane();
        options.setPadding(new Insets(0.0, 0.0, 10.0, 0.0));
        options.setPrefWidth(940.0);
        options.setVgap(5.0);
        final HBox buttons = new HBox();
        buttons.setSpacing(15.0);
        final Button load = new Button("Load");
        load.setPrefWidth(60.0);
        load.setOnAction(e -> this.loadWindow());
        final Button create = new Button("Create");
        create.setPrefWidth(60.0);
        create.setOnAction(e -> this.createWindow());
        final Button save = new Button("Save");
        save.setPrefWidth(60.0);
        save.setOnAction(e -> this.saveWindow());
        final Button clear = new Button("Clear");
        clear.setPrefWidth(60.0);
        clear.setOnAction(e -> {
            PrimaryWindow.unsorted.clear();
            PrimaryWindow.sorted.clear();
        });
        final Button help = new Button("Help");
        help.setPrefWidth(60.0);
        help.setOnAction(e -> this.helpWindow());
        buttons.getChildren().addAll(load, create, save, clear, help);
        options.add((Node)buttons, 0, 0);
        final MenuBar menus = new MenuBar();
        menus.setPrefWidth(940.0);
        final Menu sort = new Menu("Sorting Methods");
        final MenuItem select = new MenuItem("Selection Sort");
        select.setOnAction(e -> {
            if (!this.isMatrix && !this.isDouble) {
                Selection.selectionSort(this.intArray);
            }
            else if (!this.isMatrix && this.isDouble) {
                Selection.selectionSort(this.doubleArray);
            }
        });
        final MenuItem insert = new MenuItem("Insertion Sort");
        insert.setOnAction(e -> {
            if (!this.isMatrix && !this.isDouble) {
                Insertion.insertionSort(this.intArray);
            }
            else if (!this.isMatrix && this.isDouble) {
                Insertion.insertionSort(this.doubleArray);
            }
        });
        final MenuItem merge = new MenuItem("Merge Sort");
        merge.setOnAction(e -> {
            if (!this.isMatrix && !this.isDouble) {
                Merge.sort(this.intArray);
            }
            else if (!this.isMatrix && this.isDouble) {
                Merge.sort(this.doubleArray);
            }
        });
        final MenuItem quick = new MenuItem("Quick Sort");
        quick.setOnAction(e -> {
            if (!this.isMatrix && !this.isDouble) {
                Quick.sort(this.intArray);
            }
            else if (!this.isMatrix && this.isDouble) {
                Quick.sort(this.doubleArray);
            }
        });
        sort.getItems().addAll(select, insert, merge, quick);
        final Menu other = new Menu("Other Methods");
        final MenuItem perm = new MenuItem("Permutation Lex.");
        perm.setOnAction(e -> this.permLexWindow());
        final MenuItem tsp = new MenuItem("TSP");
        tsp.setOnAction(e -> this.tspWindow());
        other.getItems().addAll(perm, tsp);
        menus.getMenus().addAll(sort, other);
        options.add((Node)menus, 0, 1);
        final GridPane left = new GridPane();
        left.setVgap(5.0);
        final Label usLabel = new Label("Unsorted Values . . . . . . . . . .");
        (PrimaryWindow.unsorted = new TextArea()).setEditable(false);
        PrimaryWindow.unsorted.setPrefSize(460.0, 580.0);
        left.add((Node)usLabel, 0, 0);
        left.add((Node)PrimaryWindow.unsorted, 0, 1);
        final GridPane right = new GridPane();
        right.setVgap(5.0);
        final Label sLabel = new Label("Sorted Values . . . . . . . . . .");
        (PrimaryWindow.sorted = new TextArea()).setEditable(false);
        PrimaryWindow.sorted.setPrefSize(460.0, 580.0);
        right.add((Node)sLabel, 0, 0);
        right.add((Node)PrimaryWindow.sorted, 0, 1);
        root.setTop((Node)options);
        root.setLeft((Node)left);
        root.setRight((Node)right);
        final Scene scene = new Scene((Parent)root, 960.0, 720.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void loadWindow() {
        final Stage stage = new Stage();
        stage.setTitle("Load-N-Sort: Load File");
        stage.setWidth(480.0);
        stage.setHeight(190.0);
        stage.setResizable(false);
        final GridPane root = new GridPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        root.setVgap(10.0);
        root.setHgap(10.0);
        final Label prompt = new Label("Enter file path: ");
        final TextField path = new TextField();
        path.setPrefWidth(390.0);
        final Label error = new Label();
        final Button browse = new Button("Browse");
        browse.setPrefWidth(60.0);
        browse.setOnAction(e -> {
            final FileChooser chooser = new FileChooser();
            chooser.setTitle("Please Choose a File");
            final File file = chooser.showOpenDialog((Window)stage);
            path.setText(file.getAbsolutePath());
        });
        final CheckBox matrix = new CheckBox("Is this a matrix?");
        matrix.setOnAction(e -> this.isMatrix = !this.isMatrix);
        final Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            if (path.getText() == null || path.getText().isEmpty()) {
                error.setText("Please enter a file path and try again.");
            }
            else if (matrix.isSelected()) {
                try {
                    this.loadMatrix(path.getText());
                }
                catch (Exception ex) {
                    error.setText("Something went terribly wrong. Try again.");
                }
            }
            else if (!matrix.isSelected()) {
                try {
                    this.loadArray(path.getText());
                }
                catch (Exception ex) {
                    error.setText("Something went terribly wrong. Try again.");
                }
            }
            stage.close();
        });
        root.add((Node)prompt, 0, 0);
        root.add((Node)path, 0, 1);
        root.add((Node)browse, 1, 1);
        root.add((Node)error, 0, 2);
        root.add((Node)matrix, 0, 3);
        root.add((Node)enter, 0, 4);
        final Scene scene = new Scene((Parent)root, 480.0, 190.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void createWindow() {
    }
    
    private void saveWindow() {
        final Stage stage = new Stage();
        stage.setTitle("Load-N-Sort: Save File");
        stage.setWidth(480.0);
        stage.setHeight(190.0);
        stage.setResizable(false);
        final GridPane root = new GridPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        root.setVgap(10.0);
        root.setHgap(10.0);
        final Label prompt = new Label("Choose file destination: ");
        final TextField path = new TextField();
        path.setPrefWidth(390.0);
        final Label error = new Label();
        final Button browse = new Button("Browse");
        browse.setPrefWidth(60.0);
        browse.setOnAction(e -> {
            final FileChooser chooser = new FileChooser();
            chooser.setTitle("Please Choose a Destination");
            final File file = chooser.showSaveDialog((Window)stage);
            path.setText(file.getAbsolutePath());
        });
        final CheckBox matrix = new CheckBox("Is this a matrix?");
        final Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            try {
                final File file = new File(path.getText());
                this.writeFile(file);
            }
            catch (Exception ex) {
                error.setText("Something went terribly wrong. Try again.");
            }
            stage.close();
        });
        root.add((Node)prompt, 0, 0);
        root.add((Node)path, 0, 1);
        root.add((Node)browse, 1, 1);
        root.add((Node)error, 0, 2);
        root.add((Node)matrix, 0, 3);
        root.add((Node)enter, 0, 4);
        final Scene scene = new Scene((Parent)root, 480.0, 190.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void helpWindow() {
        final Stage stage = new Stage();
        stage.setTitle("Analysis of Algorithms: Help / Instructions");
        stage.setWidth(600.0);
        stage.setHeight(720.0);
        stage.setResizable(false);
        final BorderPane root = new BorderPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        final TextArea instruct = new TextArea();
        instruct.setEditable(false);
        instruct.setWrapText(true);
        instruct.setText("Load-N-Sort User Guide: \n\n");
        root.setCenter((Node)instruct);
        final Scene scene = new Scene((Parent)root, 600.0, 720.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void permLexWindow() {
        final Stage stage = new Stage();
        stage.setTitle("Load-N-Sort: Input Permutation");
        stage.setWidth(480.0);
        stage.setHeight(230.0);
        stage.setResizable(false);
        final GridPane root = new GridPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        root.setVgap(10.0);
        root.setHgap(10.0);
        final Label prompt = new Label();
        prompt.setText("Please write a permutation separated with commas.");
        final TextField input = new TextField();
        input.setPromptText("Ex. 1,4,7,2,5,3,6,9,8,0");
        input.setPrefWidth(460.0);
        final Label numPrompt = new Label();
        numPrompt.setText("How many permutations do you want?");
        final TextField num = new TextField();
        num.setPromptText("Ex. 10");
        final Label error = new Label();
        final Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            PermutationLex.permutationLex(input.getText(), Integer.parseInt(num.getText()), PrimaryWindow.sorted);
            stage.close();
        });
        root.add((Node)prompt, 0, 0);
        root.add((Node)input, 0, 1);
        root.add((Node)numPrompt, 0, 2);
        root.add((Node)num, 0, 3);
        root.add((Node)error, 0, 4);
        root.add((Node)enter, 0, 5);
        final Scene scene = new Scene((Parent)root, 480.0, 230.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void tspWindow() {
        final Stage stage = new Stage();
        stage.setTitle("Load-N-Sort: Input TSP Matrix");
        stage.setWidth(480.0);
        stage.setHeight(240.0);
        final GridPane root = new GridPane();
        root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        root.setVgap(10.0);
        root.setHgap(10.0);
        final Label prompt = new Label();
        prompt.setText("Please load an adjacency matrix: ");
        final TextField input = new TextField();
        input.setPrefWidth(375.0);
        final Button browse = new Button("Browse");
        browse.setOnAction(e -> {
            final FileChooser chooser = new FileChooser();
            chooser.setTitle("Please choose a matrix to load");
            final File matrix = chooser.showOpenDialog((Window)stage);
            input.setText(matrix.getAbsolutePath());
        });
        final Label error = new Label();
        final Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            try {
                this.loadMatrix(input.getText());
                if (this.isDouble) {
                    TravelSalesPerson.setupTSP(this.doubleMatrix);
                }
                else {
                    TravelSalesPerson.setupTSP(this.intMatrix);
                }
            }
            catch (Exception ex) {
                Logger.getLogger(PrimaryWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage.close();
        });
        root.add((Node)prompt, 0, 0);
        root.add((Node)input, 0, 1);
        root.add((Node)browse, 1, 1);
        root.add((Node)error, 0, 4);
        root.add((Node)enter, 0, 5);
        final Scene scene = new Scene((Parent)root, 480.0, 240.0);
        stage.setScene(scene);
        stage.show();
    }
    
    private void loadMatrix(final String path) throws Exception {
        int rowSize = 0;
        int colSize = 0;
        final File file = new File(path);
        final FileReader fr = new FileReader(file);
        final BufferedReader br = new BufferedReader(fr);
        br.mark(10000);
        String line;
        while ((line = br.readLine()) != null) {
            ++rowSize;
        }
        br.reset();
        String[] row = br.readLine().split(",");
        colSize = row.length;
        br.reset();
        this.isDouble = (line = br.readLine()).contains(".");
        if (this.isDouble) {
            this.doubleMatrix = new double[rowSize][colSize];
        }
        else {
            this.intMatrix = new int[rowSize][colSize];
        }
        final int rowCount = 0;
        br.reset();
        for (int i = 0; i < rowSize; ++i) {
            row = br.readLine().split(",");
            for (int j = 0; j < colSize; ++j) {
                if (this.isDouble) {
                    this.doubleMatrix[i][j] = Double.parseDouble(row[j]);
                }
                else {
                    this.intMatrix[i][j] = Integer.parseInt(row[j]);
                }
            }
        }
        br.close();
        fr.close();
        PrimaryWindow.unsorted.clear();
        for (int i = 0; i < colSize; ++i) {
            for (int j = 0; j < rowSize; ++j) {
                if (this.isDouble) {
                    PrimaryWindow.unsorted.appendText("[" + i + " " + j + "] = ");
                    PrimaryWindow.unsorted.appendText(this.doubleMatrix[i][j] + "          ");
                }
                else {
                    PrimaryWindow.unsorted.appendText("[" + i + " " + j + "] = ");
                    PrimaryWindow.unsorted.appendText(this.intMatrix[i][j] + "          ");
                }
            }
            PrimaryWindow.unsorted.appendText("\n");
        }
    }
    
    private void loadArray(final String path) throws Exception {
        int arraySize = 0;
        final File file = new File(path);
        final FileReader fr = new FileReader(file);
        final BufferedReader br = new BufferedReader(fr);
        br.mark(10000);
        String line;
        while ((line = br.readLine()) != null) {
            ++arraySize;
        }
        br.reset();
        this.isDouble = (line = br.readLine()).contains(".");
        if (this.isDouble) {
            this.doubleArray = new double[arraySize];
        }
        else {
            this.intArray = new int[arraySize];
        }
        int count = 0;
        br.reset();
        while ((line = br.readLine()) != null) {
            if (this.isDouble) {
                this.doubleArray[count] = Double.parseDouble(line);
                ++count;
            }
            else {
                this.intArray[count] = Integer.parseInt(line);
                ++count;
            }
        }
        br.close();
        fr.close();
        PrimaryWindow.unsorted.clear();
        for (int i = 0; i < arraySize; ++i) {
            if (this.isDouble) {
                PrimaryWindow.unsorted.appendText("[" + i + "] = " + this.doubleArray[i]);
            }
            else {
                PrimaryWindow.unsorted.appendText("[" + i + "] = " + this.intArray[i]);
            }
            PrimaryWindow.unsorted.appendText("\n");
        }
    }
    
    private void writeFile(final File file) throws Exception {
        final String[] content = PrimaryWindow.sorted.getText().split("\n");
        final FileWriter fw = new FileWriter(file);
        final BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < content.length; ++i) {
            bw.write(content[i]);
            bw.write("\n");
        }
        bw.close();
        fw.close();
    }
}

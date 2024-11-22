package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BasketballQ1 extends AppCompatActivity {
    private int [] imagenesPreguntas={
        R.drawable.jordan,
        R.drawable.equiposnba,
        R.drawable.triple,
        R.drawable.anotadores,
        R.drawable.paris
    };
    private String[] questions = {
            "¿Cuantos campeonatos de la NBA ha ganado Michael Jordan?",
            "¿Que equipo tiene mas titulos en la historia de la NBA?",
            "¿Cuanto vale un tiro desde fuera del area?",
            "¿Quien es el maximo anotador en la historia de la NBA (temporada regular)?",
            "¿En que año fue introducido el baloncesto como deporte olimpico?"
    };
    private String[][] answers = {
            {"4", "5", "6", "7"},
            {"Los Ángeles Lakers", "Golden State Warrior", "Chicago Bulls", "Boston Celtics"},
            {"1", "2", "3", "4"},
            {"Kareem Abdul-Jabbar", "LeBron James", "Karl Malone", "Kobe Brayant"},
            {"1928", "1936", "1948", "1952"}
    };
    // Índice de respuestas correctas - Ocupamos un arreglo con las respuestas correctas
    private int[] correctAnswers = {2, 3, 2, 1, 1};

    // Necesitamos las variables para la lógica en la puntuación y empezar con el contador.
    private int currentQuestion = 0;
    private int score = 0;

    // Se genera la lista para almacenar las respuestas seleccionadas por el usuario
    private int[] selectedAnswers = new int[questions.length];

    // en este apartado son los elementos de la interfaz son componentes para mostrar estatico o dinamico en la pantalla
    private TextView questionText;
    private RadioGroup answersGroup;
    private TextView resultText;
    private Button nextButton;
    private ImageView imagePregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basketball_q1);

        // Inicializamos elementos de la interfaz para utilizarlos.
        questionText = findViewById(R.id.pregunta_text);
        answersGroup = findViewById(R.id.respuestas_group);
        resultText = findViewById(R.id.result_text);
        nextButton = findViewById(R.id.next_button);
        imagePregunta = findViewById(R.id.image_pregunta);

        // Esta función muestra la primera pregunta
        setQuestion();

        //Le damos la accion del boton "Siguiente con SetOnClickListener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }
    //Configurar la pregunta actual y las opciones
    private void setQuestion() {
        questionText.setText(questions[currentQuestion]);

        RadioButton answer1 = findViewById(R.id.respuesta1);
        RadioButton answer2 = findViewById(R.id.respuesta2);
        RadioButton answer3 = findViewById(R.id.respuesta3);
        RadioButton answer4 = findViewById(R.id.respuesta4);

        answer1.setText(answers[currentQuestion][0]);
        answer2.setText(answers[currentQuestion][1]);
        answer3.setText(answers[currentQuestion][2]);
        answer4.setText(answers[currentQuestion][3]);

        //Cambia la imagen de la pregunta actual
        imagePregunta.setImageResource(imagenesPreguntas[currentQuestion]);

        // Limpiar selección anterior (debemos limpiar el grupo)
        answersGroup.clearCheck();
        resultText.setVisibility(View.GONE);
    }
    // Verificar si la respuesta es correcta - Empieza la comparación.
    private void checkAnswer() {
        int selectedAnswer = answersGroup.indexOfChild(findViewById(answersGroup.getCheckedRadioButtonId()));

        if (selectedAnswer == -1) {
            // Muestra un mensaje si no se seleccionó ninguna respuesta
            Toast.makeText(this, "Selecciona una respuesta", Toast.LENGTH_SHORT).show();
        } else {
            // Almacenar la respuesta seleccionada
            selectedAnswers[currentQuestion] = selectedAnswer;

            if (selectedAnswer == correctAnswers[currentQuestion]) {
                score++; // Respuesta correcta
            }
            // Pasa a la siguiente pregunta o muestra el resultado final
            currentQuestion++;
            if (currentQuestion < questions.length) {
                setQuestion();
            } else {
                showFinalResults(); // Muestra los resultados completos al final
            }
        }
    }
    // Aqui mostramos un resumen de respuestas correctas e incorrectas
    private void showFinalResults() {
        StringBuilder resultSummary = new StringBuilder();
        resultSummary.append("Tu puntuación: ").append(score).append("/").append(questions.length).append("\n\n");

        for (int i = 0; i < questions.length; i++) {
            resultSummary.append("Pregunta ").append(i + 1).append(": ").append(questions[i]).append("\n");
            resultSummary.append("Tu respuesta: ").append(answers[i][selectedAnswers[i]]).append("\n");

            if (selectedAnswers[i] == correctAnswers[i]) {
                resultSummary.append("Correcto!\n\n");
            } else {
                resultSummary.append("Incorrecto. La respuesta correcta es: ").append(answers[i][correctAnswers[i]]).append("\n\n");
            }
        }
        questionText.setText(resultSummary.toString()); // Muestra el resumen en el TextView
        answersGroup.setVisibility(View.GONE); // Oculta el RadioGroup de respuestas
        nextButton.setVisibility(View.GONE); // Oculta el botón de siguiente
        imagePregunta.setVisibility(View.VISIBLE);
    }
}
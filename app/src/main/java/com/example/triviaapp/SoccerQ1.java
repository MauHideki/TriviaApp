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

public class SoccerQ1 extends AppCompatActivity {
    private int [] imagenesPreguntas = {
            R.drawable.soccerq1,
            R.drawable.trofeocopamundial,
            R.drawable.delanteros,
            R.drawable.finalchampions,
            R.drawable.logobarca

    };
    private String [] questions = {
            "¿En qué año se celebró la primera Copa Mundial de la FIFA?",
            "¿Qué país ha ganado más Copas del Mundo?",
            "¿Qué jugador ha marcado más goles en la historia de los mundiales?",
            "¿Qué equipo ganó la UEFA Champions League en 2020?",
            "¿Quién es el máximo goleador histórico del FC Barcelona?"
    };
    private String[][] answers ={
            {"1930", "1942", "1950", "1966"},
            {"Alemania", "Argentina", "Brasil", "Italia"},
            {"Pelé", "Miroslav Klose", "Ronaldo Nazario", "Lionel Messi"},
            {"Liverpool", "Real Madrid", "Bayern Múnich", "Chelsea"},
            {"Luis Suárez", "Ronaldinho", "Lionel Messi", "Samuel Eto'o"}
    };
    //Indice de respuestas correctas
    private int [] correctAnswers={0,2,1,2,2};

    //Necesitamos variables para la logica de puntuacion
    private int currentQuestion=0;
    private int score =0;

    //Se genera la lista para almacenar las respuestas seleccionadas por el usuario
    private int [] selectedAnswers = new int[questions.length];

    //En este apartado son los elementos de la interfaz son componentes estaticos o dinamicos en pantalla
    private TextView questionText;
    private RadioGroup answersGroup;
    private TextView resultText;
    private Button nextButton;
    private ImageView imagePregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soccer_q1);

        //Inicializamos elementos de la interfaz para utilizarlos.
        questionText=findViewById(R.id.pregunta_text);
        answersGroup=findViewById(R.id.respuestas_group);
        resultText=findViewById(R.id.result_text);
        nextButton=findViewById(R.id.next_button);
        imagePregunta=findViewById(R.id.image_pregunta);

        //Esta funcion muestra la primera pregunta
        setQuestion();

        //Le damos la accion del boton "Siguiente" con SetClicListener
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

        //Limpiar seleccion anterior (se debe de limpiar el grupo)
        answersGroup.clearCheck();
        resultText.setVisibility(View.GONE);
    }
    //Verificar si la respuestas es correcta - Empieza la comparación.
    private void checkAnswer(){
        int selectedAnswer = answersGroup.indexOfChild(findViewById(answersGroup.getCheckedRadioButtonId()));

        if (selectedAnswer == -1){
            //Muestra un mensaje sino se selecciono ninguna respuesta
            Toast.makeText(this,"Selecciona una respuesta", Toast.LENGTH_SHORT).show();
        }else {
            //Almacenar la respuesta seleccionada
            selectedAnswers[currentQuestion]=selectedAnswer;

            if (selectedAnswer==correctAnswers[currentQuestion]){
                score++; //Respuesta correcta
            }
            //Pasa a la siguiente pregunta o muestra el resultado final
            currentQuestion++;
            if (currentQuestion<questions.length){
                setQuestion();
            } else {
                showFinalResults(); //Muestra los resultados completos al final
            }
        }
    }
    //Aqui mostramos un resumen de respuestas correctas e incorrectas
    private void showFinalResults() {
        StringBuilder resultSummary = new StringBuilder();
        resultSummary.append("Tu puntuacion: ").append(score).append("/").append(questions.length).append("\n\n");

        for (int i =0; i <questions.length;i++){
            resultSummary.append("Pregunta ").append(i + 1).append(": ").append(questions[i]).append("\n");
            resultSummary.append("Tu respuesta: ").append(answers[i][selectedAnswers[i]]).append("\n");

            if (selectedAnswers[i]== correctAnswers[i]){
                resultSummary.append("Correcto! \n\n");
            } else {
                resultSummary.append("Incorrecto. La respuestas correcta es: ").append(answers[i][correctAnswers[i]]).append("\n\n");
            }
        }
        questionText.setText(resultSummary.toString()); //Muestra el resultado
        answersGroup.setVisibility(View.GONE); // Oculta el RadioGroup de respuestas
        nextButton.setVisibility(View.GONE); // Oculta el boton de siguiente
        imagePregunta.setVisibility(View.VISIBLE);
    }
}
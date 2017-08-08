# Implementação de um widget RecyclerView
Nesse tutorial, aprenderemos a trabalhar com o componente RecyclerView. Ele surgiu para substituir o bom e velho ListView. Sua implementação apresenta uma estrutura mais performática, abrange métodos para criação de animações em listas e permite uma maior escalabilidade dos itens criados.

----------

Para iniciarmos, precisamos adicionar o Widget RecyclerView como dependência do projeto. Para isso, abra seu arquivo build.gradle e adicione a seguinte linha na seção de dependências.

```xml
compile 'com.android.support:recyclerview-v7:25.1.1'
```

----------

Uma vez adicionada, sincronize seu projeto com o gradle.
Para esse tutorial, criaremos uma classe de cursos. Vamos adicionar uma série de cursos em um ArrayList e vamos iterar esses itens em nosso RecyclerView. O modelo da classe de curso está exibido abaixo:

```java
public class CursoItem {

    private int id;
    private String titulo;
    private String turno;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "CursoItem{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", turno='" + turno + '\'' +
                '}';
    }
}
```

Após a criação dessa classe, poderemos iniciar a codificação de nosso RecyclerView. O primeiro passo é criarmos o Widget em si na Activity desejada. Para isso, implemente as seguintes linhas de código em seu arquivo de layout.

```xml
<android.support.v7.widget.RecyclerView
    android:id="@+id/recyclerViewCursos"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

Isso permitirá que o RecyclerView seja renderizado em nosso layout.


----------

Com isso, teremos em nossa aplicação o contêiner de nosso Widget. Para continuarmos, iremos criar o layout dos itens exibidos em nossa lista. Siga o modelo abaixo.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content" android:paddingBottom="@dimen/activity_vertical_margin">

    <TextView
        android:id="@+id/textViewTitulo"
        android:layout_width="match_parent"
        android:textSize="24sp"
        android:layout_height="match_parent" />

    <TextView
        android:id="@+id/textViewTurno"
        android:layout_width="match_parent"
        android:textSize="16sp"
        android:textColor="#898989"
        android:layout_height="wrap_content" />
</LinearLayout>
```

Cada item adicionado em nosso RecyclerView será representado por esse arquivo de layout.


----------

No método onCreate de nossa Activity, devemos implementar as seguintes linhas de código:

```java
public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        RecyclerView recyclerViewCursos = (RecyclerView) findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));
    }
}
```

Na linha 1, estamos capturando a instancia de nosso RecyclerView e, logo em seguida, atribuímos um gerenciador de layout ao nosso Widget.


----------

Partiremos agora para a implementação das funcionalidades de nosso RecyclerView. Para isso, criaremos, primeiramente, uma classe responsável pelo gerenciamento de nosso ViewHolder. 
O ViewHolder é responsável por gerenciar cada item exibido na lista. Nossa aplicação contará com duas classes principais para a geração de nosso RecyclerView: A classe CursoItemRecyclerAdapter ficará responsável por gerenciar nosso RecyclerView e nossa classe CursoItemViewHolder ficará responsável por gerenciar os itens de nosso recycler.
Para continuar, siga conforme o modelo abaixo para a criação da classe CursoItemViewHolder:

```java
public class CursoItemViewHolder extends RecyclerView.ViewHolder {
    TextView textViewTitulo;
    TextView textViewTurno;

    public CursoItemViewHolder(View itemView) {
        super(itemView);

        textViewTitulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
        textViewTurno = (TextView) itemView.findViewById(R.id.textViewTurno);
    }
}
```

Com isso, podemos receber as instancias de nossos TextViews dentro de nosso Widget.


----------

Iniciaremos agora a implementação das funcionalidades de nosso componente RecyclerView. Para isso, necessitamos criar uma classe que receberá nossa lista de cursos e injetará os mesmos dentro de nosso recycler. Essa classe será responsável gerenciar todos os eventos que ocorrerem à um item da lista.
Para implementá-la, siga conforme o código abaixo. 

```java
public class CursoItemRecyclerAdapter extends RecyclerView.Adapter<CursoItemViewHolder> {
    @Override
    public CursoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CursoItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
```

Criaremos uma classe que representará nosso RecyclerView. Essa, estenderá a classe Adapter do RecyclerView e passaremos como coringa, nossa classe CursoItemViewHolder. Essa classe possui alguns métodos que precisam ser pré-implementados. Abordaremos cada um em seguida.


----------

Em nosso método onCreateViewHolder da classe CursoItemRecyclerAdapter, devemos implementar as seguintes linhas de código:

```java
public CursoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_curso_item, parent, false);
    return new CursoItemViewHolder(layoutView);
}
```

Nosso método onCreateViewHolder tem, por padrão, o retorno de um objeto do tipo CursoItemViewHolder. Retornaremos esse objeto nele, porém, adicionaremos algumas informações à ele. Como nossa classe CursoItemViewHolder aguarda, no método construtor, um objeto do tipo View, criaremos esse objeto baseado em nosso arquivo de layout recycler_curso_item.
Para isso, utilizaremos a classe LayoutInflater para passar o contexto de nossa activity, definir o layout à ser utilizado e nosso objeto ViewGroup. Através dessa classe, nossa view será carregada para nosso objeto layoutView que será passado por parâmetro para nosso ViewHolder


----------

Vamos agora implementar o recebimento da lista de cursos em nosso recycler. Para isso, criaremos um método construtor que recebe como parâmetro um objeto do tipo List. Faça conforme o quadro à seguir:

```java
public class CursoItemRecyclerAdapter extends RecyclerView.Adapter<CursoItemViewHolder> {
    private List<CursoItem> mLista;

    public CursoItemRecyclerAdapter(List<CursoItem> lista) {
        mLista = lista;
    }
    
    // Outros método ocultados...
}
```

Trata-se de método simples. Recebe a lista no construtor e adiciona os valores à propriedade mLista 😃 


----------

Agora que possuímos nossa lista de cursos, poderemos continuar as implementações de nosso recycler. Alteraremos, primeiramente, o método getItemCount para que o mesmo retorne o total de itens de nossa propriedade lista. Altere o método conforme o quadro abaixo:

```java
@Override
public int getItemCount() {
    return mLista.size();
}
```

E alteraremos, também, nosso método onBindViewHolder.
Esse método é responsável por injetar os valores de nossa lista no layout inserido no ViewHolder. Vamos implementá-lo da seguinte maneira:

```java
@Override
public void onBindViewHolder(CursoItemViewHolder holder, int position) {
    CursoItem cursoItem = mLista.get(position);
    holder.textViewTitulo.setText(cursoItem.getTitulo());
    holder.textViewTurno.setText(cursoItem.getTurno());
}
```

O objeto holder possui uma instancia da classe CursoItemViewHolder com o layout recycler_curso_item injetado.

Ainda na classe CursoItemRecyclerAdapter, implementaremos mais um método. Esse, será responsável por retornar o item que for clicado em nosso recycler. Faça o seguinte:

```java
// Abaixo do método getItemCount

public CursoItem getItem(int position) {
    return mLista.get(position);
}
```

----------

Para a próxima etapa (estamos se aproximando do fim), precisaremos criar uma lista de cursos para realizar nossos testes. Faremos isso dentro da nossa activity principal.
Criaremos um método que irá rodar um loop. A cada passagem, será criado um novo objeto e seu valor será adicionado à um ArrayList. Esse ArrayList será inserido no nosso RecyclerView:

```java
private ArrayList<CursoItem> buscarCursos() {
    ArrayList<CursoItem> lista = new ArrayList<>();

    int i = 1;
    do {
        CursoItem curso = new CursoItem();
        curso.setId(i);
        curso.setTitulo("Curso " + i);

        if (i % 2 == 1) {
            curso.setTurno("Matutino");
        } else {
            curso.setTurno("Vespertino");
        }

        lista.add(curso);
        
        i++;
    } while (i < 10);

    return lista;
}
```

----------

Após a implementação do método acima, precisamos definir essa lista de cursos como o adaptador de nosso recycler:

```java
// Defina a seguinte propriedade em sua activity
public class TelaPrincipal extends AppCompatActivity {
    CursoItemRecyclerAdapter mAdapter;
    
    // Omitiremos o resto do código da Activity
}
```

Algumas linhas depois…

```java
// Omitiremos o resto do código da Activity
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tela_principal);

    RecyclerView recyclerViewCursos = (RecyclerView) findViewById(R.id.recyclerViewCursos);
    recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));

    // Adapter implementado
    CursoItemRecyclerAdapter mAdapter = new CursoItemRecyclerAdapter(buscarCursos());
    recyclerViewCursos.setAdapter(mAdapter);
}
```

Após essa implementação. Pode testar a aplicação 😃 


----------

Vamos implementar agora o evento de clique em nosso recycler

Para essa implementação, necessitaremos de uma classe responsável por capturar o gesto de clique e disparar repassar a posição da lista para nosso feedback. Utilizaremos a classe RecyclerOnItemClickListener. Essa classe tem como objetivo fazer a implementação do método OnClickListener através da classe GestureDetector. Faça a implementação da seguinte classe em sua aplicação:

```java
public class RecyclerOnItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private GestureDetector mGestureDetector;

    public RecyclerOnItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
```

Uma vez que a classe se encontra em sua aplicação, o listener de clique fica disponível para seu recycler.
Portanto, vamos implementá-lo 😃 

Vamos fazer o seguinte. Criaremos um método dentro de nossa activity responsável por receber a posição do item clicado. Dentro desse método recuperar o curso através do adaptador enviado ao recycler.

```java
// Abaixo de private ArrayList<CursoItem> buscarCursos() ...

private void onItemClicado(int position) {
    CursoItem cursoItem = mAdapter.getItem(position);

    Toast.makeText(getApplicationContext(), cursoItem.getTitulo(), Toast.LENGTH_LONG).show();
}
```

Basicamente esse método recebe a posição selecionada e busca pelo índice no mAdapter definido anteriormente. Em seguida, exibimos um Toast com o título do curso selecionado.

Agora, precisamos implementar o evento de clique em nosso recycler;

```java
recyclerViewCursos.addOnItemTouchListener(new RecyclerOnItemClickListener(this, new RecyclerOnItemClickListener.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {
        onItemClicado(position);
    }
}));
```

Esse código implementa o listener de clique em nosso recycler. Passamos para o método addOnItemTouchListener (nátivo do RecyclerView) uma instância de nossa classe RecyclerOnItemClickListener. Precisamos passar para ela o contexto de nossa aplicação e um objeto do tipo OnItemClickListener. Através desse objeto, é calculado a posição de clique. Obrigatoriamente, devemos implementar o método onItemClick que realizará o callback do evento de clique. Ele receberá um objeto contendo a view do item clicado e sua devida posição. Passaremos essa posição para o método onItemClicado (criado anteriormente). Ele realizará o callback e mostrará em tela o Toast contendo o título do curso.
Bem… É isso…
😬 


----------

Com esses métodos é possível gerar suas listas, criar callbacks de clique e trabalhar com diferentes layout, animações e modelos de implementação do RecyclerView. Espero que tenha ficado bem entendido e divirta-se!

😉 


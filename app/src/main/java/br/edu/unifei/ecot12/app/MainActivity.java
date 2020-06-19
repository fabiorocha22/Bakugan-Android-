package br.edu.unifei.ecot12.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.edu.unifei.ecot12.bakugan.AtributoEnum;
import br.edu.unifei.ecot12.bakugan.Bakugan;
import br.edu.unifei.ecot12.bakugan.CampoDeBatalha;
import br.edu.unifei.ecot12.bakugan.CartaDeHabilidade;
import br.edu.unifei.ecot12.bakugan.Desfavoravel;
import br.edu.unifei.ecot12.bakugan.Favoravel;
import br.edu.unifei.ecot12.bakugan.Humano;
import br.edu.unifei.ecot12.bakugan.R;
import br.edu.unifei.ecot12.dao.BakuganDao;
import br.edu.unifei.ecot12.dao.CampoDeBatalhaDao;
import br.edu.unifei.ecot12.dao.CartadeHabilidadeDao;
import br.edu.unifei.ecot12.dao.DatabaseHelper;
import br.edu.unifei.ecot12.dao.DesfavoravelDao;
import br.edu.unifei.ecot12.dao.FavoravelDao;
import br.edu.unifei.ecot12.dao.HumanoDao;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE_ID = "br.edu.unifei.ecot12.bakugan.ID";
    private DatabaseHelper dh;
    private HumanoDao humanoDao;
    private BakuganDao bakuganDao;
    private List<Humano> humanos;
    private Humano h;
    private int firstId = 0;
    private CartadeHabilidadeDao cartadeHabilidadeDao;
    private CampoDeBatalhaDao campoDeBatalhaDao;
    private FavoravelDao favoravelDao;
    private DesfavoravelDao desfavoravelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dh = new DatabaseHelper(MainActivity.this);

        try {
            TableUtils.clearTable(dh.getConnectionSource(), Humano.class);
            TableUtils.clearTable(dh.getConnectionSource(), Bakugan.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        humanos = initializeDataBase();

        try {
            humanoDao = new HumanoDao(dh.getConnectionSource());
            bakuganDao = new BakuganDao(dh.getConnectionSource());
            cartadeHabilidadeDao = new CartadeHabilidadeDao(dh.getConnectionSource());
            campoDeBatalhaDao = new CampoDeBatalhaDao(dh.getConnectionSource());
            favoravelDao = new FavoravelDao(dh.getConnectionSource());
            desfavoravelDao = new DesfavoravelDao(dh.getConnectionSource());
            for(Humano itHumano : humanos){
                Log.i("POSSUI", "HAB: " + itHumano.getCartasDeHabilidade().size());
                if(humanoDao.create(itHumano) == 1){
                    for(CartaDeHabilidade itCartaDeHabilidade : itHumano.getCartasDeHabilidade()){
                        cartadeHabilidadeDao.create(itCartaDeHabilidade);
                    }
                    for(CampoDeBatalha itCampoDeBatalha : itHumano.getCamposDeBatalha()){
                        campoDeBatalhaDao.create(itCampoDeBatalha);
                    }
                    for(Bakugan itBakugan : itHumano.getBakugans()){
                        bakuganDao.create(itBakugan);
                    }
                    firstId = firstId == 0 ? (int) itHumano.getId() : firstId;
                }
            }

            //GET ALL LINES
            /*Log.i("Script"," ");
            Log.i("Script", "GET ALL LINES " + humanos.size());*/
            humanos = humanoDao.queryForAll();
            for(Humano itHumano : humanos){
                //Log.i("Script", itHumano.getNome());
                Log.i("Script", "Name: " + itHumano.getNome() +
                        "\nID: " + itHumano.getId() + "\nIMAGEM: " + itHumano.getImagem() +
                        "\nQtd Habilidade: " + itHumano.getCartasDeHabilidade().iterator()
                            .next().getNome()+
                        "\nQtd Campo: " + itHumano.getCamposDeBatalha().size() +
                        "\nQtd Bakugans: " + itHumano.getBakugans().size());
            }

            //Imprime a lista de humanos
            Log.i("Script", "GET ALL LINES " + humanos.size());
            GridView gridview = (GridView) findViewById(R.id.gridBakugans);
            HumanoAdapter adapter = new HumanoAdapter(this, humanos);
            gridview.setAdapter(adapter);


            EditText editText = (EditText) findViewById(R.id.editText);
            editText.setText(Long.toString(humanos.get(0).getId()));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void battle(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ID, message);
        startActivity(intent);
    }

    public void editPerson(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, EditHumanActivity.class);
        intent.putExtra(EXTRA_MESSAGE_ID, message);
        startActivity(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        dh.close();
    }

    private List<Humano> initializeDataBase(){
        List<Humano> humanos = new ArrayList<>();
        Humano h;
        CartaDeHabilidade cartaDeHabilidade;
        Favoravel favoravel;
        Desfavoravel desfavoravel;
        CampoDeBatalha campoDeBatalha;
        Collection<CampoDeBatalha> campos = new ArrayList<>();

        favoravel = new Favoravel();
        favoravel.setValor(140);

        desfavoravel = new Desfavoravel();
        desfavoravel.setValor(100);
        //----------------------------------------------------------------------------------
        h = new Humano("Masquerade", "masquerade");

        Collection<Bakugan> bakugans = new ArrayList<>();
        bakugans.add(new Bakugan("Hydranoid", 100, 50,
                AtributoEnum.DARKUS, 450, h));
        bakugans.add(new Bakugan("Centipoid", 100, 50,
                AtributoEnum.DARKUS, 400, h));
        bakugans.add(new Bakugan("Laserman", 100, 50,
                AtributoEnum.DARKUS, 390, h));
        h.setBakugans(bakugans);

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Shadow Haze", AtributoEnum.DARKUS, 60, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.DARKUS, 50, h),
                new CartaDeHabilidade("Trident Of Doom", AtributoEnum.DARKUS, 300, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.DARKUS, h),
                new CampoDeBatalha(AtributoEnum.DARKUS, h),
                new CampoDeBatalha(AtributoEnum.PYRUS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Daniel Kuso", "dan");
        h.setBakugans(Arrays.asList(
                new Bakugan("Dragonoid", 100, 50,
                        AtributoEnum.PYRUS, 380, h),
                new Bakugan("Falconeer", 100, 50,
                        AtributoEnum.PYRUS, 340, h),
                new Bakugan("Saurus", 100, 50,
                        AtributoEnum.PYRUS, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.PYRUS, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.PYRUS, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.PYRUS, 50, h)
        ));

        Log.i("DANIEL", h.getCartasDeHabilidade().size() + "");

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.PYRUS, h),
                new CampoDeBatalha(AtributoEnum.PYRUS, h),
                new CampoDeBatalha(AtributoEnum.DARKUS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Shun Kazami", "shun");
        h.setBakugans(Arrays.asList(
                new Bakugan("Skyress", 100, 50,
                        AtributoEnum.VENTUS, 380, h),
                new Bakugan("Monarus", 100, 50,
                        AtributoEnum.VENTUS, 340, h),
                new Bakugan("Ravenoide", 100, 50,
                        AtributoEnum.VENTUS, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.VENTUS, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.VENTUS, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.VENTUS, 50, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.VENTUS, h),
                new CampoDeBatalha(AtributoEnum.VENTUS, h),
                new CampoDeBatalha(AtributoEnum.HAOS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Runo Misaki", "runo");
        h.setBakugans(Arrays.asList(
                new Bakugan("Tigrerra", 100, 50,
                        AtributoEnum.HAOS, 380, h),
                new Bakugan("Griffon", 100, 50,
                        AtributoEnum.HAOS, 340, h),
                new Bakugan("Siege", 100, 50,
                        AtributoEnum.HAOS, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.HAOS, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.HAOS, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.HAOS, 50, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.HAOS, h),
                new CampoDeBatalha(AtributoEnum.HAOS, h),
                new CampoDeBatalha(AtributoEnum.VENTUS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Marucho Marukura\n", "marucho");
        h.setBakugans(Arrays.asList(
                new Bakugan("Preyas", 100, 50,
                        AtributoEnum.AQUOS, 380, h),
                new Bakugan("Siege", 100, 50,
                        AtributoEnum.AQUOS, 340, h),
                new Bakugan("Stinglash", 100, 50,
                        AtributoEnum.AQUOS, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.AQUOS, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.AQUOS, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.AQUOS, 50, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.AQUOS, h),
                new CampoDeBatalha(AtributoEnum.AQUOS, h),
                new CampoDeBatalha(AtributoEnum.SUBTERRA, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Alice Gehabich\n", "alice");
        h.setBakugans(Arrays.asList(
                new Bakugan("Bee Striker", 100, 50,
                        AtributoEnum.VENTUS, 380, h),
                new Bakugan("Mantriz", 100, 50,
                        AtributoEnum.SUBTERRA, 340, h),
                new Bakugan("Centipoid", 100, 50,
                        AtributoEnum.DARKUS, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.VENTUS, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.SUBTERRA, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.DARKUS, 50, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.DARKUS, h),
                new CampoDeBatalha(AtributoEnum.SUBTERRA, h),
                new CampoDeBatalha(AtributoEnum.PYRUS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        h = new Humano("Julie Makimoto\n\n", "julie");
        h.setBakugans(Arrays.asList(
                new Bakugan("Gorem", 100, 50,
                        AtributoEnum.SUBTERRA, 380, h),
                new Bakugan("Tuskor", 100, 50,
                        AtributoEnum.SUBTERRA, 340, h),
                new Bakugan("Rattleoid", 100, 50,
                        AtributoEnum.SUBTERRA, 270, h)));

        h.setCartasDeHabilidade(Arrays.asList(
                new CartaDeHabilidade("Juiz de Fogo", AtributoEnum.SUBTERRA, 100, h),
                new CartaDeHabilidade("Vulcânia Burst", AtributoEnum.SUBTERRA, 150, h),
                new CartaDeHabilidade("Torching Breath", AtributoEnum.SUBTERRA, 50, h)
        ));

        h.setCamposDeBatalha(Arrays.asList(
                new CampoDeBatalha(AtributoEnum.SUBTERRA, h),
                new CampoDeBatalha(AtributoEnum.SUBTERRA, h),
                new CampoDeBatalha(AtributoEnum.AQUOS, h)
        ));

        humanos.add(h);

        //----------------------------------------------------------------------------------

        return humanos;
    }
}

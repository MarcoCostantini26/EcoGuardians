package com.example.ecoguardians

import org.json.JSONObject

class JsonAnimal {
    val animal1 = """
        {
            "name": "Koala",
            "position": "Oceania",
            "numberSpecies": "47.000 - 85.000",
            "classification": "Mammifero",
            "averageLife": "10-12",
            "animalDescription": "Specie iconica del continente australiano, il koala è un marsupiale arboricolo strettamente legato alle foreste con prevalenza di eucalipto, le cui foglie sono pressoché la sua unica fonte di alimentazione.
Animale solitario e attivo soprattutto di notte, conduce una vita sedentaria muovendosi molto lentamente. Raramente scende a terra dove rischierebbe di essere facile preda dei dingo, i cani selvatici australiani. 
Dal corpo tozzo, con occhi piccoli, grandi orecchie arrotondate e con coda rudimentale, possiede zampe possenti con dita prensili in parte opponibili e lunghi artigli funzionali ad arrampicarsi e fare presa sui tronchi e i rami. 
Il maschio, più grande della femmina di circa il 50%, raggiunge i 14 kg di peso e possiede una particolare ghiandola toracica che secerne una sostanza odorosa con cui marca gli alberi e che attira la femmina durante il periodo riproduttivo. 
La sua dieta è altamente specializzata, nutrendosi quasi esclusivamente di foglie di eucalipto di cui privilegia solo pochissime specie, circa 20 sulle oltre 600 specie che vivono in Australia. I koala bevono molto di rado e traggono i liquidi di cui necessitano direttamente dalle foglie. 
La gestazione dura appena 25-35 giorni in quanto, come in tutti i marsupiali, l’unico piccolo (i parti gemellari sono assai rari) nasce prematuro e completa il suo sviluppo all’interno del marsupio. Dopo circa 6 mesi il piccolo inizia ad uscire dal marsupio per essere trasportato dalla mamma sul dorso. ",
            "threats": "Oggi la principale minaccia per il koala è rappresentata dal cambiamento climatico responsabile, tra le altre cose, dell’incremento di periodi siccitosi che comportano il verificarsi di incendi più lunghi, frequenti e devastanti di quanto accadeva in passato. È proprio a causa del perpetuarsi degli incendi, durati ben 9 mesi, che nel 2019 e 2020 sono morti oltre 60.000 koala e sono bruciati quasi 19 milioni di ettari di foreste. I koala sono animali altamente specializzati e non riescono ad adattarsi a tali repentini cambiamenti. La siccità li costringe a scendere a terra alla ricerca di fonti alternative di acqua rendendoli particolarmente vulnerabili anche nei confronti di incidenti e predazioni, mentre l’aumento della CO2 nell’atmosfera determina un abbassamento della qualità nutritiva delle foglie di eucalipto portando a frequenti casi di malnutrizione che possono anche comportare l’abbassamento del tasso si fertilità e la morte degli animali. ",
            "whatYouCanDo": "Secondo i ricercatori australiani per salvare i koala dal rischio di estinzione bisognerebbe congelare il loro sperma. Gli scienziati dell’Università di Newcastle, nel Nuovo Galles del Sud, hanno suggerito di allestire un biolab con il ‘liquido riproduttivo’ raccolto che poi potrebbe essere usato come parte di un programma di allevamento, per garantire il futuro della specie di marsupiali e migliorare la sua diversità genetica.",
            "seriousLink": "https://sostieni.wwf.it/adotta-koala/",
            "latitude": -34.0833333 ,
            "longitude": 151.0833333
        }
    """

    val animal2 = """
        {
            "name": "Orso Polare",
            "position": "Polo Nord",
            "numberSpecies": "30.000",
            "classification": "Mammifero",
            "averageLife": "30",
            "animalDescription": "L’orso polare è tra i più grandi carnivori terrestri del Pianeta, ma il suo nome scientifico, Ursus maritimus, ci ricorda anche che trascorre la maggior parte della vita in mare o in sua prossimità. Sappiamo che il maestoso simbolo dell’Artico è oggi in serio pericolo di estinzione, ma stime precise sono difficili da ottenere poiché questa specie vive, a densità molto basse, in regioni impervie e disabitate. I dati disponibili ci dicono che poco meno di 30.000 esemplari, divisi in 19 sottopopolazioni, vivono oggi nelle regioni artiche.
L’orso polare presenta uno strato di grasso sottocutaneo spesso fino a 11 cm che ha lo scopo di tenerlo al caldo, specialmente durante le immersioni in acque gelide. I maschi adulti misurano mediamente, partendo dal naso fino alla punta della coda, dai 2 ai 2,5 metri e pesano tra i 400 e i 600 kg. Le dimensioni delle femmine sono molto più ridotte, infatti esse non superano la metà del peso dei maschi. Gli orsi polari sono animali prevalentemente solitari, ma non mancano occasioni di interazione con i propri simili, come durante il periodo riproduttivo dove rimangono in coppia per circa una settimana. I cuccioli, generalmente due, nascono dopo circa due mesi di gestazione ed hanno un peso di appena 600 grammi; dopo cinque mesi iniziano a mangiare cibo solido, ma non vengono completamente svezzati fino al compimento dei 2-3 anni di vita. 
La loro dieta è costituita principalmente da carne di foca, in modo particolare foca degli anelli, sebbene si nutrano anche di altre specie come balene beluga, giovani esemplari di tricheco, piccoli mammiferi, pesci, uccelli marini e delle loro uova. ",
            "threats": "Gli effetti dei cambiamenti climatici stanno mettendo a rischio la vita dell’animale simbolo dell’Artico. Non passa anno senza che venga registrato un nuovo record di temperatura e di massimo valore di ritiro dei ghiacciai. Ciò influisce sulla vita dell’orso polare cambiandone drasticamente le abitudini: con la diminuzione della banchisa gli orsi faticano a trovare cibo perché, pur essendo ottimi nuotatori, devono affrontare spostamenti sempre più lunghi e più frequenti in mare aperto. Inoltre, gli ecosistemi artici sono sempre più spesso interessati dalle attività di estrazione di minerali, petrolio e gas, oltre che dalle attività industriali e traffico marittimo che sono cause di incessante inquinamento.",
            "whatYouCanDo": "Sono tantissime le azioni quotidiane che possiamo fare per ridurre il nostro impatto sul pianeta: inquinando meno, ridurremo il rischio dello scioglimento dei ghiacciai e potremo contribuire a salvare l’orso polare dall’estinzione.",
            "seriousLink": "https://sostieni.wwf.it/adotta-orsopolare/",
            "latitude": 80.051983 ,
            "longitude": -23.996978
        }
    """

    val animal3 = """
        {
            "name": "Tigre",
            "position": "Foresta",
            "numberSpecies": "3.900",
            "classification": "Mammifero",
            "averageLife": "10-15",
            "animalDescription": "La tigre è il più grande felino vivente, simbolo di potenza e agilità. La sua stessa morfologia è perfetta per catturare e uccidere grandi prede con un colpo solo. La tigre può raggiungere anche i 3 metri di lunghezza, coda compresa, il peso è molto variabile e va dai 140 kg nei maschi della tigre di Sumatra ai 300 kg nella tigre siberiana. Le femmine sono sempre più piccole.
Un tempo diffusa in quasi tutta l’Asia, la tigre ha perso il 96% del suo areale originario in 100 anni; il numero complessivo delle tigri, difficile da stimare, è comunque di poche migliaia di individui, sparsi su un continente e in popolazioni isolate. Il colore di base del mantello di una tigre varia da un arancio ruggine scuro a un giallo arancio più chiaro e presenta strisce verticali scure che sono uniche per ogni individuo. La parte inferiore e alcune parti del muso sono di colore bianco-crema. Il colore di ogni sottospecie di tigre varia a seconda della sua posizione: la tigre siberiana tende a essere più chiara con strisce meno marcate, mentre la tigre del bengala ha una forte colorazione arancione con strisce scure e marcate. 
Anche la lunghezza della pelliccia dipende dal luogo in cui si trova. La tigre siberiana ha una pelliccia più lunga e densa che le permette di stare al caldo nel clima freddo della Siberia. 
Le tigri si possono trovare in una grande varietà di habitat: foreste tropicali, foreste sempreverdi, boschi, praterie, zone rocciose, paludi e savane. Preferiscono le aree con una fitta copertura e l’accesso a una fonte d’acqua. 
Le femmine raggiungono la maturità sessuale intorno ai 3-4 anni di età, mentre i maschi maturano a circa 4-5 anni. Dopo circa 100 giorni di gestazione, la femmina partorisce da uno a sette cuccioli. Nascono con gli occhi chiusi e indifesi, per questo, per il primo mese di vita il raggio d’azione della femmina resta è ridotto nei pressi della tana. Quando i cuccioli raggiungono le 8 settimane di vita sono pronti ad accompagnare la madre ed è a questo punto che iniziano a nutrirsi di carne. Diventano indipendenti intorno ai 18 mesi, ma tendono a rimanere con la madre fino all’età di almeno 2 anni. ",
            "threats": "Da secoli il felino più grande del mondo viene costantemente minacciato dall’uomo. Le principali minacce per le popolazioni di tigri oggi sono la perdita/frammentazione dell’habitat e il bracconaggio. Le tigri vengono cacciate per sport, per paura, per superstizione e perché possono aggredire il bestiame domestico. Secondo alcune culture, le loro ossa tritate rendono più forti, mentre secondo altre, gli organi genitali possono aumentare la virilità e gli occhi sono in grado di curare malattie della vista. La pelliccia della tigre è considerata una merce pregiata, come simbolo di lusso e di potere.  
I cuccioli vengono venduti come animali da compagnia. Si stima che negli Stati Uniti ci siano circa 5.000 tigri in cattività di cui solo il 6% risiede in zoo e altre strutture accreditate dall’Associazione degli zoo e degli acquari. La stragrande maggioranza di queste tigri è di proprietà privata e vive nei cortili delle persone, nelle attrazioni stradali e nelle strutture di allevamento private. 
Ad oggi in natura rimangono solo 3.900 tigri, un dato che mostra un declino costante e un serio rischio per le popolazioni di tigri. Delle nove sottospecie, tre si sono già estinte: la tigre di Bali, di Java e del Caspio. In India, da sempre considerata patria principale delle tigri, una stima approssimativa del governo ha contato la presenza di circa 2.967 esemplari. ",
            "whatYouCanDo": "Operazione Tigre: era il 1973 quando il wwf ha lanciato questa campagna di comunicazione, la prima in assoluto a difesa di una specie. Una dei maggiori risultati è stato il lancio del Progetto “Tigre in India”, dove un piano nazionale di conservazione della durata di sei anni e l’istituzione di 15 nuove riserve portarono all’aumento del 30% della popolazione di tigri. Un risultato importante per diminuire il rischio di estinzione, ma non in grado di invertire la rotta: le tigri hanno continuato a diminuire. È così che nel 2010 il wwf ha inaugurato l’Anno della Tigre. La campagna ha avuto il suo culmine nel novembre del 2010, quando i rappresentanti dei governi di tutti i Paesi che ancora ospitano tigri selvatiche si sono riuniti nel primo Summit mondiale sulla Tigre. In questa occasione è stato messo a punto un piano per fermare la pericolosa tendenza che rischia di portare all’estinzione delle tigri, con una grande sfida: raddoppiare il numero di tigri entro il 2022. Il wwf è in prima linea per raggiungere questo obiettivo. Le strategie adottate tendono sia a misure di emergenza per salvare le tigri in pericolo, sia ad azioni a lungo termine per assicurare loro un futuro. ",
            "seriousLink": "https://sostieni.wwf.it/adotta-tigre/",
            "latitude": 26.12 ,
            "longitude": 70.309
        }
    """

    val animal4 = """
        {
            "name": "Pinguino",
            "position": "Antartide",
            "numberSpecies": "75",
            "classification": "Mammifero",
            "averageLife": "30",
            "animalDescription": "Con i suoi 115 cm di altezza il pinguino imperatore è il più grande dei pinguini viventi. È il simbolo indiscusso dell’Antartide, continente dove vive sopportando freddissime temperature che possono raggiungere anche i -60°C. Gli effetti dei cambiamenti climatici e la presenza di attività antropiche minacciano l’habitat e la sopravvivenza di questa specie.Il pinguino imperatore è una delle 18 specie di pinguini esistenti, vive solo nel continente antartico dove è presente con una popolazione stimata tra le 280 e le 290 mila coppie. Si nutre di piccoli pesci, calamari e altri cefalopodi, ma soprattutto di krill, piccoli crostacei che sono parte costituente dello zooplancton. È l’unico uccello antartico che nidifica durante l’inverno, tra maggio e giugno (all’inizio dell’inverno australe), al termine del quale le femmine depongono un solo uovo di grandi dimensioni dal peso di circa 450 grammi. In seguito alla deposizione la femmina torna velocemente in mare aperto poiché è lei che si occupa della ricerca di cibo in questa fase; il maschio si occuperà di covare tenendo l’uovo sulle zampe, proteggendolo dal ghiaccio grazie ad una piega cutanea presente sul ventre, e aspettando il ritorno della femmina per la schiusa, dopo circa 65 giorni. Il ritorno della femmina, dopo due mesi trascorsi in mare, sarà fondamentale per la crescita del pulcino e per la sua alimentazione. Se la femmina tarderà, il maschio alimenterà il nuovo nato per i primi giorni con una secrezione densa molto nutriente detta “latte di pinguino”. ",
            "threats": "La popolazione del pinguino imperatore si è ridotta molto negli ultimi decenni: a Pointe G. il numero degli esemplari sembrerebbe addirittura diminuito del 50% negli anni passati; la situazione però, dagli anni 70 ad oggi, sembra seguire un lento ma continuo miglioramento. I rischi per questa specie sono principalmente costituiti dagli effetti del cambiamento climatico come lo scioglimento dei ghiacciai e la conseguente riduzione della banchisa. 
I cambiamenti climatici e la riduzione delle superfici di ghiaccio minacciano la sopravvivenza delle colonie e spingono i giovani pinguini prima del tempo ad affrontare il mare ed i predatori. La crisi climatica in Antartide ha un effetto dirompente anche sui banchi di krill, la fonte di cibo primaria non solo per i pinguini, ma per molte specie antartiche. ",
            "whatYouCanDo": "Il programma del wwf mira a tutelare, gestire correttamente e ripristinare la biodiversità nell’ambiente antartico affrontando tre temi fondamentali: 
-La protezione delle risorse e della biodiversità nella regione antartica; 
-La riduzione degli effetti di attività antropiche come la pesca intensiva e l’inquinamento delle acque marine; 
-Il ripristino delle specie e degli ecosistemi minacciati. ",
            "seriousLink": "https://sostieni.wwf.it/adotta-pinguino/",
            "latitude": -74.212006 ,
            "longitude": -315.975629
        }
    """

    val animal5 = """
        {
            "name": "Elefante",
            "position": "Foresta",
            "numberSpecies": "2.000 - 2.500",
            "classification": "Mammifero",
            "averageLife": "70-80",
            "animalDescription": "L’elefante è il più grande mammifero terrestre oggi vivente sul nostro Pianeta: il maschio di un elefante africano può superare i 3 m di altezza e pesare più di 5 tonnellate. Le zanne d’avorio sono i denti incisivi superiori, che nei vecchi maschi possono arrivare a 2-3 m di lunghezza. Oggi in Africa vivono due specie, l’elefante di savana e quello di foresta, mentre quello asiatico vive nell’Asia meridionale e nel Sud-est asiatico, dall’India all’Indonesia settentrionale. Gli elefanti sono animali dall’elevata socialità, vivono in gruppi costituiti da femmine imparentate e dalla loro prole, a capo dei quali c’è la femmina anziana, la cosiddetta “matriarca”. Trascorrono fino a 18 ore al giorno ad alimentarsi di sostanze vegetali come germogli, frutti, foglie, radici e rami che strappano grazie all’elevata mobilità della proboscide e che ingeriscono in gran quantità, fino a 150 kg al giorno. Si nutrono sia di giorno, sia di notte. Gli animali si mantengono in contatto tra loro comunicando anche attraverso gli infrasuoni emessi a bassa frequenza, non udibili dall’uomo. L’acqua è particolarmente importante nella vita degli elefanti non solo per dissetarsi, ma anche per i frequenti bagni di fango utili per regolare la temperatura corporea e per liberarsi dai parassiti. Il cucciolo nasce dopo 22 mesi di gestazione e pesa già circa 100 kg, dopo pochi giorni è già in grado di seguire il branco. Lo svezzamento totale avviene dopo circa 3 anni, sebbene a 9 mesi di età cominci a integrare la sua dieta con sostanze vegetali. Le tre specie di elefanti differiscono per le dimensioni e alcuni caratteri come ad esempio le zanne che, sempre presenti in entrambi i sessi negli elefanti africani sono invece assenti nelle femmine di elefante asiatico. ",
            "threats": "Tutte e tre le specie di elefante sono a rischio di estinzione soprattutto per la perdita e frammentazione dell’habitat causata dall’espansione umana e dalla conseguente conversione di habitat naturali in aree agricole e insediamenti abitativi. A ciò si aggiungono il bracconaggio per il commercio illegale dell’avorio delle zanne e i conflitti con le attività antropiche che, a causa del contatto sempre più stretto fra gli animali e le aree abitate, sono sempre più frequenti. Anche in Asia le estese piantagioni di palma da olio, molto appetibili per gli elefanti, portano a conflitti che spesso si risolvono con uccisioni illegali degli animali. Nonostante le leggi di protezione di cui gli elefanti godono, il commercio dell’avorio comporta ogni anno l’uccisione di molti animali, considerando che l’avorio viene venduto al mercato nero a prezzi molto elevanti e che una zanna di un maschio adulto raggiunge il peso di 50 kg e una lunghezza fino a 2,5 m. Nei due anni di pandemia da Covid-19, si è assistito a un aumento del bracconaggio a causa della minore presenza di turisti, minore sorveglianza e una marcata crisi socio-economica in molti Paesi. ",
            "whatYouCanDo": "Il wwf si batte da anni per la conservazione dell’habitat degli elefanti attraverso la collaborazione con i governi per l’istituzione di nuove riserve, la severa applicazione delle leggi nazionali di protezione e il mantenimento di “corridoi” fra le aree protette per facilitare le migrazioni e gli spostamenti che gli elefanti compiono alla ricerca di cibo e acqua. La pluri-decennale esperienza sul campo ha portato a comprendere che è fondamentale minimizzare i conflitti fra uomini ed elefanti. Tra le varie iniziative in difesa dell’elefante c’è un programma congiunto tra WWF e IUCN (Unione Mondiale per la Conservazione della Natura) chiamato ETIS, “Elephant Trade Information System”: una banca dati gestita dal TRAFFIC, la rete internazionale che si occupa del controllo del commercio della fauna selvatica minacciata. 
Un altro programma attivo è MIKE, “Monitoring Illegal Kill Elephant Programme”, sostenuto dal WWF e altri enti internazionali presenti in tutti i Paesi compresi nell’areale dell’elefante africano. L’obiettivo è raccogliere dati sulla mortalità degli elefanti e diminuire le uccisioni illegali, per poi aiutare i governi locali a risolvere i conflitti tra i pachidermi e alcune attività economiche.",
            "seriousLink": "https://sostieni.wwf.it/adotta-elefante/",
            "latitude": 0.650143,
            "longitude": 100.618004
        }
    """

    val animal6 = """
        {
            "name": "Stambecco",
            "position": "Montagna",
            "numberSpecies": "47.000",
            "classification": "Mammifero",
            "averageLife": "24",
            "animalDescription": "Lo stambecco delle Alpi (Capra ibex L. 1758), anche noto come stambecco
                                    alpino, bouquetin, o semplicemente stambecco, è una specie di capra
                                    selvatica diffusa sulle montagne dell'Arco alpino. È una specie
                                    sessualmente dimorfica: i maschi sono più grandi e presentano corna
                                    più lunghe e ricurve rispetto alle femmine. Il colore del suo mantello
                                    è tipicamente grigio brunastro. Gli stambecchi alpini tendono a
                                    vivere su terreni ripidi e accidentati vicino al limite delle nevicate.
                                    Sono animali socievoli, sebbene maschi e femmine adulti rimangono
                                    separati per la maggior parte dell'anno, riunendosi solo per accoppiarsi,
                                    raggruppandosi in quattro gruppi distinti; gruppi di maschi adulti,
                                    gruppi di femmine con cuccioli, gruppi di giovani e gruppi di sesso
                                    misto.",
            "threats": " La specie mostra sofferenza di fronte al progressivo mutamento del clima, 
                           che sull’arco alpino si presenta sempre più torrido. Le prospettive per
                           il futuro dello stambecco non sono affatto rosee, addirittura si parla di 
                           rischio di estinzione. Proprio per questo problema si sta osservando la 
                           tragica migrazione verso quote maggiori alla ricerca di nuovi habitat 
                           idonei alla propria sopravvivenza. Salendo in quota lo stambecco incontrerà
                           crescenti difficoltà nel reperire cibo in quantità e qualità idonee.
                           l’anticipo della primavera possa comportare una riduzione dell’erba fresca,
                           essenziale alle femmine nel periodo di svezzamento e allattamento dei piccoli, 
                           ovvero tra giugno e luglio. Con uno scioglimento anticipato e rapido delle nevi,
                           a giugno l’erba è già in fase di invecchiamento, ricca di fibre e povera di proteine. 
                           La carenza di nutrienti apportati dal latte materno comporta ovviamente una riduzione 
                           nelle possibilità di sopravvivenza dei nuovi nati. Inoltre, l’anticipo della primavera 
                           può comportare una riduzione dell’erba fresca, essenziale alle femmine nel periodo di 
                           svezzamento e allattamento dei piccoli, ovvero tra giugno e luglio. Con uno scioglimento 
                           anticipato e rapido delle nevi, a giugno l’erba è già in fase di invecchiamento, 
                           ricca di fibre e povera di proteine. La carenza di nutrienti apportati dal latte 
                           materno comporta ovviamente una riduzione nelle possibilità di sopravvivenza dei nuovi nati. ",
            "whatYouCanDo": " Un primo passo in aiuto della specie può essere rappresentato dall’evitare
                                di sottoporre a stress gli esemplari, inducendoli a spostarsi verso pareti
                                rocciose dove l’animale si sente più sicuro ma inevitabilmente trova meno
                                erba e soprattutto molto povera in nutrienti. Situazione che si verifica
                                sovente a seguito del passaggio di un elicottero. ",
            "seriousLink": " https://www.centrorecuperoselvatici.it/tag/stambecco/ ",
            "latitude": 45.518491,
            "longitude": 7.271953
        }
    """

    val animal7 = """
        {
            "name": "Orso Marsicano",
            "position": "Bosco",
            "numberSpecies": "60",
            "classification": "Mammifero",
            "averageLife": "35-40",
            "animalDescription": "L'orso bruno marsicano (Ursus arctos marsicanus Altobello, 1921)
                                    è un mammifero onnivoro della famiglia degli Ursidi: si tratta di
                                    una sottospecie dell'orso bruno comune endemica dell'Italia
                                    centro-meridionale, nella regione storico-geografica della Marsica,
                                    dove, nell'areale centrale corrispondente al parco nazionale d'Abruzzo,
                                    Lazio e Molise. Presenta corporatura abbastanza tozza e tarchiata,
                                    anche se più slanciata rispetto a quella di altre sottospecie di orso bruno
                                    di maggiori dimensioni. La testa è grande e tondeggiante, con muso cilindrico
                                    e piuttosto schiacciato dotato di un grosso tartufo nerastro.
                                    Il pelo è bruno-fulvo uniforme su tutto il corpo, con tendenza all'inscurimento
                                    sulla parte distale degli arti, i quali sono grossi e forti.
                                    Gli occhi sono piccoli e di color nocciola, mentre le orecchie sono anch'esse piccole
                                    e di forma arrotondata, poste leggermente ai lati del cranio.
                                    La coda è ridotta a un moncherino di meno di 10 cm.",
            "threats": "L’orso bruno è ancora vittima della persecuzione diretta e indiretta da parte
                        dell’uomo. Bracconaggio con lacci, veleno e arma da fuoco, e mortalità accidentale
                        causata in primis dagli investimenti stradali, rappresentano minacce importanti per
                        la sopravvivenza a medio e lungo termine di questa specie. L’orso bruno marsicano
                        ha bisogno di grandi spazi e la presenza nel suo areale di strade, centri abitati,
                        ferrovie e diverse altre infrastrutture sottraggono e frammentano il suo spazio
                        vitale compromettendone la possibilità di aumentare di numero e estendere il suo areale.
                        Questi sono i motivi per cui, nonostante le nuove nascite, la popolazione non aumenta:
                        muoiono troppi orsi!",
            "whatYouCanDo": "Serve ripristinare decine di sottopassi stradali abbandonati e impraticabili
                                per gli animali, distribuire nuove recinzioni a tutela degli allevamenti,
                                installare dissuasori acustici e ottici che scoraggino gli orsi ad
                                attraversare le strade più pericolose, rimuovere le risorse trofiche che
                                attraggono gli orsi nelle aree urbane sostituendo, ad esempio, i cassonetti
                                attuali con modelli a prova di orso, favorire la convivenza con le
                                popolazioni locali superando pregiudizi e diffidenza.",
            "seriousLink": "https://sostieni.wwf.it/adotta-orso-bruno/",
            "latitude": 41.849056,
            "longitude": 13.812335
        }
    """

    val animal8 = """
        {
            "name": "Saola",
            "position": "Foresta",
            "numberSpecies": "700",
            "classification": "Mammifero",
            "averageLife": "8 - 10",
            "animalDescription": "Il saola (Pseudoryx nghetinhensis) o bue Vu Qang, uno dei mammiferi più
                                    rari del mondo, è un bovide che abita in una regione molto ristretta
                                    tra la Riserva naturale di Vu Qang e il Laos, presso il confine con
                                    il Vietnam. Il saola divenne noto alla comunità scientifica solo
                                    nel maggio del 1992, a seguito del ritrovamento in un villaggio
                                    sui monti annamiti in Vietnam di alcune paia di corna appartenenti
                                    ad un animale a quel tempo ignoto. L'analisi cromosomica ha permesso
                                    di stabilire che il saola appartiene ad un nuovo genere di ruminanti,
                                    imparentato con la mucca, il kudu, l'eland e l'anoa, e situato nella
                                    sottofamiglia Bovinae.",
            "threats": "Questa specie sta inesorabilmente scivolando verso l'estinzione a causa della
                            pressione della caccia intensiva e della scarsa gestione delle arre protette.
                            Intanto lo sviluppo umano sta invadendo le foreste e l'habitat della Saola,
                            ma secondo l'Swg «La maggiore minaccia proviene dalla caccia illegale».
                            Questi splendidi animali a quanto pare vengono catturati con lacci d'acciaio
                            piazzati dai bracconieri per catturare altri animali, come i cervi Sambar
                            (Rusa unicolor ) e Muntjac  (Muntiacus reevesi ) e gli zibetti, in gran parte
                            destinati al lucroso commercio di fauna selvatica destinato alla medicina
                            tradizionale cinese ed ai ristoranti ed ai mercati di selvaggina vietnamiti e
                            del Laos.",
            "whatYouCanDo": "Un nuovo progetto guidato dal WWF “Salvare il Saola dall’estinzione” e
                                finanziato dalla UE lavora su un nuovo metodo di ricerca del Saola che
                                impiega campioni di acqua dove sono presenti DNA ambientali.",
            "seriousLink": "https://www.wwf.it/pandanews/ambiente/il-ritorno-del-saola/",
            "latitude": 17.779354,
            "longitude": 106.180219
        }
    """

    val animal9 = """
        {
            "name": "Balenottera",
            "position": "Oceano",
            "numberSpecies": "100.000",
            "classification": "Mammifero",
            "averageLife": "80",
            "animalDescription": "La balenottera comune è un animale pelagico, vive cioè prevalentemente
                                    in mare aperto lontano dalla costa. In Italia normalmente viene osservata
                                    nei mari con profondità superiore ai 2.000 m. Ha una colorazione grigio
                                    ardesia sul dorso, bianca sul ventre e presenta una caratteristica
                                    colorazione asimmetrica della parte anteriore: la mascella destra chiara
                                    e la sinistra scura, utile per distinguere i differenti individui.",
            "threats": "L’attività di caccia condotta nello stretto di Gibilterra nella prima metà del
                        XIX secolo ha portato ad una riduzione significativa della popolazione di
                        balenottere comuni nel Mediterraneo.Tuttavia per le balenottere comuni nel
                        Mediterraneo sono le collisioni con le navi la principale causa di morte antropica,
                        soprattutto dove il traffico marittimo è più intenso. Inoltre a causa del loro
                        caratteristico metodo di alimentazione, possono catturare insieme al krill,
                        grandi quantità di microplastiche che rimangono intrappolate nei fanoni e
                        inghiottite insieme al cibo esponendole a un grave rischio di inquinamento.",
            "whatYouCanDo": "Fare donazioni a delle associazioni serie come Greenpeace o la Shepherd.
                                Parlare, documentarsi e condividere con altri la situazione dei cetacei.
                                L'informazione aiuta a creare consapevolezza e questa genera rispetto e
                                sensibilità.",
            "seriousLink": "https://sostieni.wwf.it/adotta-balena/",
            "latitude": 35.729819,
            "longitude": 19.606580
        }
    """

    val animal10 = """
        {
            "name": "Foca Monaca",
            "position": "Oceano",
            "numberSpecies": "700",
            "classification": "Mammifero",
            "averageLife": "20-30",
            "animalDescription": "Buffa e simpatica, è una delle specie di foca più rare e l’unica
                                    presente nel Mediterraneo. È considerata una delle 100 specie di
                                    mammiferi più minacciati al mondo. Può raggiungere i 2,5 metri di
                                    lunghezza e i 300 kg di peso. Il muso ha dei lunghi ciuffi di vibrisse.
                                    Osservazioni più o meno regolari avvengono ancora nei nostri mari,
                                    in Basilicata, in Sicilia e intorno alle isole del Tirreno.",
            "threats": "n sacc",
            "whatYouCanDo": "Diverse organizzazioni internazionali da anni si sono però attivate con
                                campagne di sensibilizzazione per garantire la sopravvivenza degli ultimi
                                animali ancora in vita. Nel 2019 l’Associazione europea per i mammiferi
                                acquatici (Eaam) raddoppierà i suoi sforzi per aiutare questa specie
                                attraverso l’organizzazione greca MOm, che salva e riabilita i cuccioli
                                di foca monaca e poi li rimette in libertà. Mentre tutti gli sforzi stanno
                                dando i risultati sperati, resta ancora molto lavoro da fare per essere
                                davvero ottimisti sul pieno recupero della specie. Quest’anno Sea Shepherd
                                Grecia e Sea Shepherd Italia per la prima volta uniranno le forze per
                                lanciare la “Campagna Difesa Foca Monaca”, per difendere la foca monaca
                                del Mediterraneo, Monachus monachus. La campagna è già iniziata in Grecia
                                e sta per partire anche in Italia.",
            "seriousLink": "https://sostieni.wwf.it/adotta-foca/",
            "latitude": 17.908548,
            "longitude": -16.618088
        }
    """
}

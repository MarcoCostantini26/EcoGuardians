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
            "animalDescription": "koala description",
            "threats": "n sacc",
            "whatYouCanDo": "n sacc",
            "seriousLink": "n sacc",
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
            "animalDescription": "orso description",
            "threats": "n sacc",
            "whatYouCanDo": "n sacc",
            "seriousLink": "n sacc",
            "latitude": 58.76841 ,
            "longitude": -94.164963
        }
    """

    val animal3 = """
        {
            "name": "Tigre",
            "position": "Foresta",
            "numberSpecies": "3.900",
            "classification": "Mammifero",
            "averageLife": "10-15",
            "animalDescription": "tigre description",
            "threats": "n sacc",
            "whatYouCanDo": "n sacc",
            "seriousLink": "n sacc",
            "latitude": 26.12 ,
            "longitude": 70.309
        }
    """

    val animal4 = """
        {
            "name": "Rinoceronte",
            "position": "Savana",
            "numberSpecies": "75",
            "classification": "Mammifero",
            "averageLife": "30",
            "animalDescription": "rinoceronte di giava description",
            "threats": "n sacc",
            "whatYouCanDo": "n sacc",
            "seriousLink": "n sacc",
            "latitude": -6.757097 ,
            "longitude": 105.297631
        }
    """

    val animal5 = """
        {
            "name": "Elefante",
            "position": "Foresta",
            "numberSpecies": "2.000 - 2.500",
            "classification": "Mammifero",
            "averageLife": "70-80",
            "animalDescription": "elefante description",
            "threats": "n sacc",
            "whatYouCanDo": "n sacc",
            "seriousLink": "n sacc",
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

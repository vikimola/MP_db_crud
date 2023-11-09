CREATE DATABASE BookStore;

CREATE TABLE Book
(
    id        BIGSERIAL,
    title     VARCHAR(255),
    author    VARCHAR(255),
    publisher VARCHAR(255),
    price     DOUBLE PRECISION,
    stock     INTEGER,
    PRIMARY KEY (id)

);


CREATE TABLE Client
(
    id        BIGSERIAL,
    firstName     VARCHAR(255),
    lastName    VARCHAR(255),
    phoneNumber VARCHAR(255),
    PRIMARY KEY (id)
);


CREATE TABLE Purchase
(
    bookId       BIGINT REFERENCES Book(id),
    clientId       BIGINT REFERENCES Client(id),
    numberSold     INTEGER,
    dateOfPurchase    DATE,
    PRIMARY KEY(bookId, clientId)
);


insert into Book (id, title, author, publisher, price, stock) values (1, 'Another Me', 'Kippie', 'Menopause Complex', 780.52, 3503);
insert into Book (id, title, author, publisher, price, stock) values (2, 'Winchester ''73', 'Kaitlyn', 'OXYGEN', 380.86, 4594);
insert into Book (id, title, author, publisher, price, stock) values (3, 'Allnighter, The', 'Temple', 'Platinum Cichorium', 177.11, 8392);
insert into Book (id, title, author, publisher, price, stock) values (4, 'King Kong vs. Godzilla (Kingukongu tai Gojira)', 'Eddie', 'Amlodipine Besylate', 27.56, 3952);
insert into Book (id, title, author, publisher, price, stock) values (5, 'Koti-ikävä', 'Francesca', 'VANCOMYCIN HYDROCHLORIDE', 309.22, 9797);
insert into Book (id, title, author, publisher, price, stock) values (6, 'Night of the Shooting Stars (Notte di San Lorenzo, La)', 'Micki', 'ProCure Hand Sanitizing', 113.92, 4076);
insert into Book (id, title, author, publisher, price, stock) values (7, 'Cool Runnings', 'Di', 'Hibistat', 205.55, 2453);
insert into Book (id, title, author, publisher, price, stock) values (8, 'Lilies', 'Andee', 'Cromolyn Sodium', 375.29, 9471);
insert into Book (id, title, author, publisher, price, stock) values (9, 'Short Film About John Bolton, A', 'Dewain', 'Dove', 451.62, 1084);
insert into Book (id, title, author, publisher, price, stock) values (10, 'Heart and Souls', 'Conney', 'BB Tinted Treatment 12-hour Primer Broad Spectrum SPF 30 Sunscreen', 598.09, 3830);
insert into Book (id, title, author, publisher, price, stock) values (11, 'Eagle Eye', 'Lorenzo', 'Fluoxetine', 259.14, 27);
insert into Book (id, title, author, publisher, price, stock) values (12, 'Kidnap Syndicate', 'Dennison', 'Hydrocodone Bitartrate And Acetaminophen', 563.98, 7231);
insert into Book (id, title, author, publisher, price, stock) values (13, 'Monster Squad, The', 'Farleigh', 'UNITHROID', 989.37, 2762);
insert into Book (id, title, author, publisher, price, stock) values (14, 'Posse', 'Sarena', 'Lollicaine Mint', 970.69, 68);
insert into Book (id, title, author, publisher, price, stock) values (15, 'Kiss Me Again', 'Bernelle', 'Capzasin P Arthritis Pain Relief', 841.37, 7958);
insert into Book (id, title, author, publisher, price, stock) values (16, 'Godzilla Against MechaGodzilla (Gojira tai Mekagojira)', 'Moise', 'Allergy Relief D', 645.14, 6327);
insert into Book (id, title, author, publisher, price, stock) values (17, 'To Be or Not to Be', 'Melisa', 'TERBINAFINE HYDROCHLORIDE', 857.69, 5164);
insert into Book (id, title, author, publisher, price, stock) values (18, 'Man of the Century', 'Marita', 'Escitalopram Oxalate', 14.29, 7447);
insert into Book (id, title, author, publisher, price, stock) values (19, 'Prêt à tout', 'Layton', 'Amlodipine Besylate', 384.87, 4965);
insert into Book (id, title, author, publisher, price, stock) values (20, 'Chris Rock: Bring the Pain', 'Laurie', 'Eve Lom Daily Protection Broad Spectrum SPF 50 Sunscreen', 858.12, 7582);
insert into Book (id, title, author, publisher, price, stock) values (21, 'Last Kiss, The', 'Nanni', 'Necon', 159.25, 3871);
insert into Book (id, title, author, publisher, price, stock) values (22, 'Last Days in Vietnam', 'Robbie', 'REFRESH LACRI-LUBE', 101.93, 7887);
insert into Book (id, title, author, publisher, price, stock) values (23, 'Mantrap', 'Lydia', 'Divalproex Sodium Extended-Release', 105.75, 2701);
insert into Book (id, title, author, publisher, price, stock) values (24, 'Graceland', 'Kipp', 'Lotrimin Ultra Jock Itch', 106.68, 9748);
insert into Book (id, title, author, publisher, price, stock) values (25, 'Tekken', 'Abbey', 'Nortriptyline Hydrochloride', 836.23, 5401);
insert into Book (id, title, author, publisher, price, stock) values (26, 'Twilight of the Golds, The', 'Dael', 'IMITREX', 313.22, 4325);
insert into Book (id, title, author, publisher, price, stock) values (27, 'Beautiful Country, The', 'Skelly', 'Basil', 650.38, 9748);
insert into Book (id, title, author, publisher, price, stock) values (28, 'Immigrant, The', 'Hailey', 'Oxygen', 776.64, 6359);
insert into Book (id, title, author, publisher, price, stock) values (29, 'Class of 1999', 'Gonzalo', 'Fatigue Fighter', 465.48, 546);
insert into Book (id, title, author, publisher, price, stock) values (30, 'Two Girls and a Guy', 'Fiona', 'erythromycin', 680.09, 1035);
insert into Book (id, title, author, publisher, price, stock) values (31, 'Children Are Watching Us, The (Bambini ci guardano, I)', 'Daryle', 'Dove DermaSeries', 501.58, 5705);
insert into Book (id, title, author, publisher, price, stock) values (32, '13 Lakes', 'Maribelle', 'PULMICORT', 640.31, 9714);
insert into Book (id, title, author, publisher, price, stock) values (33, 'Brute, The (Bruto, El)', 'Ivan', 'Clindamycin', 950.03, 1242);
insert into Book (id, title, author, publisher, price, stock) values (34, 'Every Other Weekend (Un week-end sur deux)', 'Bethany', 'DILAUDID', 80.44, 3099);
insert into Book (id, title, author, publisher, price, stock) values (35, 'Dark Tower, The', 'Prinz', 'Depakote', 127.75, 3610);
insert into Book (id, title, author, publisher, price, stock) values (36, 'RoboCop 3', 'June', 'Desert Bambu', 254.15, 2143);
insert into Book (id, title, author, publisher, price, stock) values (37, 'Slappy and the Stinkers', 'Rube', 'Losartan Potassium', 429.84, 6110);
insert into Book (id, title, author, publisher, price, stock) values (38, 'Syrian Bride, The', 'Linnea', 'Little Fevers Childrens Fever Pain Reliever', 699.03, 9022);
insert into Book (id, title, author, publisher, price, stock) values (39, 'Extract', 'Jamil', 'Promethazine Hydrochloride', 909.06, 739);
insert into Book (id, title, author, publisher, price, stock) values (40, 'Atomic Cafe, The', 'Andee', 'Hydrochlorothiazide', 527.72, 6474);
insert into Book (id, title, author, publisher, price, stock) values (41, 'Asterix in Britain (Astérix chez les Bretons)', 'Jerrome', 'Sunmark Nicotine', 185.6, 2750);
insert into Book (id, title, author, publisher, price, stock) values (42, 'Beyond Outrage', 'Tuesday', 'Omeprazole', 731.84, 5097);
insert into Book (id, title, author, publisher, price, stock) values (43, 'Don''t Worry, I''m Fine (Je vais bien, ne t''en fais pas)', 'George', 'Sertraline Hydrochloride', 591.73, 3405);
insert into Book (id, title, author, publisher, price, stock) values (44, 'Funny Games', 'Gayler', 'Jantoven', 142.6, 2634);
insert into Book (id, title, author, publisher, price, stock) values (45, 'Hotel', 'Hank', 'ADVATE', 111.52, 7045);
insert into Book (id, title, author, publisher, price, stock) values (46, 'Invaders from Space', 'Gilles', 'Cough', 495.6, 4755);
insert into Book (id, title, author, publisher, price, stock) values (47, 'Miral', 'Pinchas', 'Pravastatin Sodium', 326.68, 5711);
insert into Book (id, title, author, publisher, price, stock) values (48, 'Color of Money, The', 'Jessamyn', 'Lovenox', 755.97, 5589);
insert into Book (id, title, author, publisher, price, stock) values (49, 'Late Bloomers', 'Clarke', 'Soothe and Cool Fresh Dandruff Control', 776.65, 7395);
insert into Book (id, title, author, publisher, price, stock) values (50, 'Chitty Chitty Bang Bang', 'Corinna', 'Tolterodine Tartrate', 264.93, 2575);
insert into Book (id, title, author, publisher, price, stock) values (51, 'Four Rooms', 'Packston', 'Flector', 270.84, 1420);
insert into Book (id, title, author, publisher, price, stock) values (52, 'Perrier''s Bounty', 'Winifred', 'THALITONE', 344.8, 5963);
insert into Book (id, title, author, publisher, price, stock) values (53, 'In the Name of the Father', 'Tomi', 'Daytime Cough and Cold', 359.9, 4340);
insert into Book (id, title, author, publisher, price, stock) values (54, 'Drive, He Said', 'Courtnay', 'Triamcinolone Acetonide', 136.48, 6452);
insert into Book (id, title, author, publisher, price, stock) values (55, 'Office Space', 'Lindi', 'Oxymorphone hydrochloride', 547.8, 2716);
insert into Book (id, title, author, publisher, price, stock) values (56, '588 Rue Paradis (Mother)', 'Joey', 'Hydrocortisone', 845.31, 6153);
insert into Book (id, title, author, publisher, price, stock) values (57, 'Now Is Good', 'Barnie', 'Tricor', 249.05, 8783);
insert into Book (id, title, author, publisher, price, stock) values (58, 'My First War', 'Elmore', 'PAROXETINE', 318.43, 2971);
insert into Book (id, title, author, publisher, price, stock) values (59, 'Outlaw Blues', 'Diane-marie', 'RHIZOPUS NIGRICANS', 13.04, 3094);
insert into Book (id, title, author, publisher, price, stock) values (60, 'Tintin and I', 'Wittie', 'Ketoprofen', 852.99, 6285);
insert into Book (id, title, author, publisher, price, stock) values (61, 'Castle in the Desert (Charlie Chan in Castle in the Desert)', 'Tobit', 'Guaifenesin and Codeine Phosphate', 601.44, 236);
insert into Book (id, title, author, publisher, price, stock) values (62, 'Noriko''s Dinner Table (Noriko no shokutaku)', 'Zitella', 'Gas Relief', 416.23, 6812);
insert into Book (id, title, author, publisher, price, stock) values (63, 'Pure Country', 'Allys', 'PediaCare Infants Fever Reducer Cherry', 981.53, 2883);
insert into Book (id, title, author, publisher, price, stock) values (64, 'Deadly Companions, The', 'Rollins', 'Hydroxyzine Hydrochloride', 660.04, 9668);
insert into Book (id, title, author, publisher, price, stock) values (65, 'Before and After', 'Dennis', 'Prostin', 446.58, 3874);
insert into Book (id, title, author, publisher, price, stock) values (66, 'Weirdsville', 'Nicolina', 'whole health Hand Sanitizers', 317.72, 9712);
insert into Book (id, title, author, publisher, price, stock) values (67, 'Dragon Ball Z: Super Android 13! (Doragon bôru Z 7: Kyokugen batoru!! San dai sûpâ saiyajin)', 'Adamo', 'Rexall All Day Pain Relief', 938.62, 9482);
insert into Book (id, title, author, publisher, price, stock) values (68, 'All About My Mother (Todo sobre mi madre)', 'Marlyn', 'Prostin', 260.59, 4000);
insert into Book (id, title, author, publisher, price, stock) values (69, 'Night Will Fall', 'Sayre', 'Nisoldipine', 282.79, 8481);
insert into Book (id, title, author, publisher, price, stock) values (70, 'Caddyshack II', 'Truman', 'Hand Sanitizer', 832.9, 8611);
insert into Book (id, title, author, publisher, price, stock) values (71, 'Brother of Sleep (Schlafes Bruder)', 'Willette', 'Medicated Chest', 621.13, 8325);
insert into Book (id, title, author, publisher, price, stock) values (72, 'Secretariat', 'Artus', 'Levetiracetam', 628.96, 5886);
insert into Book (id, title, author, publisher, price, stock) values (73, 'Expelled: No Intelligence Allowed', 'Elsie', 'Allergy', 820.19, 5550);
insert into Book (id, title, author, publisher, price, stock) values (74, 'Sir Arne''s Treasure', 'Shawn', 'COLGATE ULTRA BRITE ADVANCED WHITENING ALL IN ONE CLEAN MINT', 48.98, 1923);
insert into Book (id, title, author, publisher, price, stock) values (75, 'Bourne Supremacy, The', 'Marcille', 'Lamotrigine', 373.71, 8709);
insert into Book (id, title, author, publisher, price, stock) values (76, 'Brute Force', 'D''arcy', 'Rescue 5', 803.97, 3869);
insert into Book (id, title, author, publisher, price, stock) values (77, 'Turandot Project, The', 'Karlyn', 'Neutrogena Healthy Defense Daily Moisturizer', 633.42, 9653);
insert into Book (id, title, author, publisher, price, stock) values (78, 'Games of Love and Chance (L''esquive)', 'Meade', 'NARS PURE RADIANT TINTED MOISTURIZER', 857.12, 3005);
insert into Book (id, title, author, publisher, price, stock) values (79, 'Love! Valour! Compassion!', 'Melisenda', 'Prochlorperazine Maleate', 765.25, 9483);
insert into Book (id, title, author, publisher, price, stock) values (80, 'The Invitation', 'Rafe', 'Lubriderm Intense Skin Repair', 206.78, 6892);
insert into Book (id, title, author, publisher, price, stock) values (81, '40-Year-Old Virgin, The', 'Marsiella', 'hydroxyzine pamoate', 820.51, 3293);
insert into Book (id, title, author, publisher, price, stock) values (82, 'Miracle Worker, The', 'Leroy', 'Black Oak', 724.89, 5288);
insert into Book (id, title, author, publisher, price, stock) values (83, 'Kings Row', 'Selie', 'Sheep Sorrel', 375.02, 2858);
insert into Book (id, title, author, publisher, price, stock) values (84, 'Cargo', 'Beale', 'Nifedipine', 745.77, 7494);
insert into Book (id, title, author, publisher, price, stock) values (85, 'Blood Money', 'Zabrina', 'Strawberry', 211.07, 910);
insert into Book (id, title, author, publisher, price, stock) values (86, 'SpongeBob Movie: Sponge Out of Water, The', 'Clifford', 'topcare allergy relief', 217.2, 6126);
insert into Book (id, title, author, publisher, price, stock) values (87, 'Flowing (Nagareru)', 'Johannes', 'Panretin', 246.39, 404);
insert into Book (id, title, author, publisher, price, stock) values (88, 'Loputon Gehennan liekki', 'Cherin', 'Pramipexole Dihydrochloride', 930.13, 6224);
insert into Book (id, title, author, publisher, price, stock) values (89, 'Labyrinth of Lies', 'Ingra', 'Bisacodyl', 136.59, 4614);
insert into Book (id, title, author, publisher, price, stock) values (90, 'Down and Derby', 'Noellyn', 'Meclizine Hydrochloride', 577.04, 2945);
insert into Book (id, title, author, publisher, price, stock) values (91, 'Knight''s Tale, A', 'Gerrie', 'Bisacodyl Laxative', 549.02, 3232);
insert into Book (id, title, author, publisher, price, stock) values (92, 'Total Recall', 'Ade', 'Brain Power', 798.89, 194);
insert into Book (id, title, author, publisher, price, stock) values (93, 'Sun Alley (Sonnenallee)', 'Worthington', 'Dologen', 59.46, 6685);
insert into Book (id, title, author, publisher, price, stock) values (94, 'Shameless (Maskeblomstfamilien )', 'Henrietta', 'Cephalexin', 376.26, 6939);
insert into Book (id, title, author, publisher, price, stock) values (95, 'On Tour (Tournée)', 'Cristobal', 'Pedia-Lax', 263.13, 3512);
insert into Book (id, title, author, publisher, price, stock) values (96, 'Children in the Wind (Kaze no naka no kodomo)', 'Danny', 'Metoprolol succinate', 443.88, 1777);
insert into Book (id, title, author, publisher, price, stock) values (97, 'Remarkable Power', 'Melba', 'MAXALT', 218.12, 4514);
insert into Book (id, title, author, publisher, price, stock) values (98, 'Resan Till Melonia', 'Welch', 'A.H.C. C-CREAM', 431.03, 8175);
insert into Book (id, title, author, publisher, price, stock) values (99, 'Mind Reader, The', 'Hendrik', 'Desenex', 213.26, 1751);
insert into Book (id, title, author, publisher, price, stock) values (100, 'Serendipity', 'Myrle', 'Instant Hand Sanitizer', 511.2, 5534);


insert into Client (id, firstName, lastName, phoneNumber) values (1, 'Aundrea', 'McDonnell', '2932430267');
insert into Client (id, firstName, lastName, phoneNumber) values (2, 'Bunnie', 'Prazer', '5163376855');
insert into Client (id, firstName, lastName, phoneNumber) values (3, 'Lorens', 'Midner', '6431981725');
insert into Client (id, firstName, lastName, phoneNumber) values (4, 'Cindy', 'Buxcy', '3964436576');
insert into Client (id, firstName, lastName, phoneNumber) values (5, 'Lucho', 'Stibbs', '9915240667');
insert into Client (id, firstName, lastName, phoneNumber) values (6, 'Amerigo', 'Diggins', '4609058970');
insert into Client (id, firstName, lastName, phoneNumber) values (7, 'Judith', 'Lohmeyer', '5072650201');
insert into Client (id, firstName, lastName, phoneNumber) values (8, 'Shalom', 'Beazer', '3466363660');
insert into Client (id, firstName, lastName, phoneNumber) values (9, 'Keith', 'Harte', '5648628417');
insert into Client (id, firstName, lastName, phoneNumber) values (10, 'Lothario', 'MacAndie', '7598277110');
insert into Client (id, firstName, lastName, phoneNumber) values (11, 'Ninetta', 'Coultar', '6512551740');
insert into Client (id, firstName, lastName, phoneNumber) values (12, 'Emelia', 'Mucci', '5263213636');
insert into Client (id, firstName, lastName, phoneNumber) values (13, 'Derron', 'Dugall', '3274033502');
insert into Client (id, firstName, lastName, phoneNumber) values (14, 'Ky', 'Fritschel', '7706862155');
insert into Client (id, firstName, lastName, phoneNumber) values (15, 'Prudi', 'Kenningley', '6435464005');
insert into Client (id, firstName, lastName, phoneNumber) values (16, 'Janella', 'Coughan', '3733175269');
insert into Client (id, firstName, lastName, phoneNumber) values (17, 'Elsi', 'Mahaddy', '9702528040');
insert into Client (id, firstName, lastName, phoneNumber) values (18, 'Merell', 'Bunch', '7503146657');
insert into Client (id, firstName, lastName, phoneNumber) values (19, 'Nixie', 'Porter', '9276777005');
insert into Client (id, firstName, lastName, phoneNumber) values (20, 'Tobye', 'Yerrell', '5085805702');
insert into Client (id, firstName, lastName, phoneNumber) values (21, 'Hansiain', 'Jovicic', '4816089351');
insert into Client (id, firstName, lastName, phoneNumber) values (22, 'Eudora', 'Faint', '7218181829');
insert into Client (id, firstName, lastName, phoneNumber) values (23, 'Luis', 'Bertolaccini', '5306228617');
insert into Client (id, firstName, lastName, phoneNumber) values (24, 'Marsh', 'Kilkenny', '9078330892');
insert into Client (id, firstName, lastName, phoneNumber) values (25, 'Lindon', 'Jaegar', '1544639468');
insert into Client (id, firstName, lastName, phoneNumber) values (26, 'Prudy', 'Jackways', '4243070838');
insert into Client (id, firstName, lastName, phoneNumber) values (27, 'Klarika', 'Lomen', '7346239545');
insert into Client (id, firstName, lastName, phoneNumber) values (28, 'Ulises', 'Tick', '5853760954');
insert into Client (id, firstName, lastName, phoneNumber) values (29, 'Harriette', 'Martinets', '4959842546');
insert into Client (id, firstName, lastName, phoneNumber) values (30, 'Enriqueta', 'Pott', '3683048489');
insert into Client (id, firstName, lastName, phoneNumber) values (31, 'Dewain', 'Lampard', '9181185740');
insert into Client (id, firstName, lastName, phoneNumber) values (32, 'Zachariah', 'Inker', '5931793172');
insert into Client (id, firstName, lastName, phoneNumber) values (33, 'Tiffanie', 'Abell', '1735039095');
insert into Client (id, firstName, lastName, phoneNumber) values (34, 'Tallia', 'Kleinholz', '1412071975');
insert into Client (id, firstName, lastName, phoneNumber) values (35, 'Justus', 'Gustus', '3123793558');
insert into Client (id, firstName, lastName, phoneNumber) values (36, 'Sean', 'Panting', '9237485804');
insert into Client (id, firstName, lastName, phoneNumber) values (37, 'Ramsay', 'Schult', '7913233018');
insert into Client (id, firstName, lastName, phoneNumber) values (38, 'Marylou', 'Garroway', '7975044166');
insert into Client (id, firstName, lastName, phoneNumber) values (39, 'Verna', 'Andrey', '1533351794');
insert into Client (id, firstName, lastName, phoneNumber) values (40, 'Domini', 'Claydon', '2805331645');
insert into Client (id, firstName, lastName, phoneNumber) values (41, 'Kordula', 'Alcalde', '7217039636');
insert into Client (id, firstName, lastName, phoneNumber) values (42, 'Ethelind', 'Van Der Vlies', '3329028247');
insert into Client (id, firstName, lastName, phoneNumber) values (43, 'Elijah', 'Aspling', '8046298513');
insert into Client (id, firstName, lastName, phoneNumber) values (44, 'Lynda', 'Glaves', '3022114175');
insert into Client (id, firstName, lastName, phoneNumber) values (45, 'Harriot', 'Neve', '5878820201');
insert into Client (id, firstName, lastName, phoneNumber) values (46, 'Kearney', 'Thackham', '8234193748');
insert into Client (id, firstName, lastName, phoneNumber) values (47, 'Frances', 'Behrendsen', '1525142424');
insert into Client (id, firstName, lastName, phoneNumber) values (48, 'Angelika', 'Straughan', '6302231782');
insert into Client (id, firstName, lastName, phoneNumber) values (49, 'Morse', 'Gaymer', '6694019257');
insert into Client (id, firstName, lastName, phoneNumber) values (50, 'Collen', 'Linder', '1871791164');
insert into Client (id, firstName, lastName, phoneNumber) values (51, 'Linzy', 'Lemm', '3515056759');
insert into Client (id, firstName, lastName, phoneNumber) values (52, 'Laney', 'Edmonson', '9587651846');
insert into Client (id, firstName, lastName, phoneNumber) values (53, 'Andriana', 'Meriet', '2764319530');
insert into Client (id, firstName, lastName, phoneNumber) values (54, 'Clayborne', 'Deble', '6208672007');
insert into Client (id, firstName, lastName, phoneNumber) values (55, 'Vale', 'Thelwll', '4423686737');
insert into Client (id, firstName, lastName, phoneNumber) values (56, 'Cy', 'Andell', '3624713933');
insert into Client (id, firstName, lastName, phoneNumber) values (57, 'Atlante', 'Domerque', '2809117754');
insert into Client (id, firstName, lastName, phoneNumber) values (58, 'Rebekkah', 'Marwood', '3378283195');
insert into Client (id, firstName, lastName, phoneNumber) values (59, 'Brannon', 'Salmon', '5519120007');
insert into Client (id, firstName, lastName, phoneNumber) values (60, 'Corine', 'Dunsmuir', '7449938144');
insert into Client (id, firstName, lastName, phoneNumber) values (61, 'Dani', 'L'' Anglois', '7732723649');
insert into Client (id, firstName, lastName, phoneNumber) values (62, 'Julia', 'Handsheart', '3377988276');
insert into Client (id, firstName, lastName, phoneNumber) values (63, 'Tamma', 'Sorrell', '4583375840');
insert into Client (id, firstName, lastName, phoneNumber) values (64, 'Birgitta', 'Laroux', '5961812196');
insert into Client (id, firstName, lastName, phoneNumber) values (65, 'Nell', 'Scouller', '5351783022');
insert into Client (id, firstName, lastName, phoneNumber) values (66, 'Ellie', 'Vinsen', '5011499089');
insert into Client (id, firstName, lastName, phoneNumber) values (67, 'Adorne', 'Landrieu', '4871067742');
insert into Client (id, firstName, lastName, phoneNumber) values (68, 'Mollie', 'Heakey', '2412239300');
insert into Client (id, firstName, lastName, phoneNumber) values (69, 'Annissa', 'Gillivrie', '9531671408');
insert into Client (id, firstName, lastName, phoneNumber) values (70, 'Guglielma', 'Hancell', '2051029244');
insert into Client (id, firstName, lastName, phoneNumber) values (71, 'Glen', 'Underhill', '8889567253');
insert into Client (id, firstName, lastName, phoneNumber) values (72, 'Johannes', 'McTeer', '9529467652');
insert into Client (id, firstName, lastName, phoneNumber) values (73, 'Sanford', 'Leijs', '2543750684');
insert into Client (id, firstName, lastName, phoneNumber) values (74, 'Alf', 'St. Quentin', '8485337347');
insert into Client (id, firstName, lastName, phoneNumber) values (75, 'Vasilis', 'Ewbanck', '8544386462');
insert into Client (id, firstName, lastName, phoneNumber) values (76, 'Janice', 'Defries', '8692992203');
insert into Client (id, firstName, lastName, phoneNumber) values (77, 'Johannes', 'Giff', '4582610868');
insert into Client (id, firstName, lastName, phoneNumber) values (78, 'Cyndi', 'Giblin', '1465026097');
insert into Client (id, firstName, lastName, phoneNumber) values (79, 'Benita', 'Peppard', '7273146527');
insert into Client (id, firstName, lastName, phoneNumber) values (80, 'Matias', 'Preator', '1306520008');
insert into Client (id, firstName, lastName, phoneNumber) values (81, 'Wynn', 'MacCosto', '9077643058');
insert into Client (id, firstName, lastName, phoneNumber) values (82, 'Shari', 'Beekman', '5658016819');
insert into Client (id, firstName, lastName, phoneNumber) values (83, 'Giff', 'Dossettor', '7596535536');
insert into Client (id, firstName, lastName, phoneNumber) values (84, 'Mick', 'Bugbird', '2601613511');
insert into Client (id, firstName, lastName, phoneNumber) values (85, 'Frannie', 'Todari', '4815841533');
insert into Client (id, firstName, lastName, phoneNumber) values (86, 'Craggie', 'Harbert', '4622945648');
insert into Client (id, firstName, lastName, phoneNumber) values (87, 'Xenia', 'Seckom', '3266936340');
insert into Client (id, firstName, lastName, phoneNumber) values (88, 'Rockie', 'Eddolls', '9299827076');
insert into Client (id, firstName, lastName, phoneNumber) values (89, 'Guido', 'Bloore', '7477775354');
insert into Client (id, firstName, lastName, phoneNumber) values (90, 'Patience', 'Humpherston', '2458198873');
insert into Client (id, firstName, lastName, phoneNumber) values (91, 'Vinnie', 'Derobert', '2392331689');
insert into Client (id, firstName, lastName, phoneNumber) values (92, 'Katine', 'Staniforth', '2328199587');
insert into Client (id, firstName, lastName, phoneNumber) values (93, 'Sharleen', 'Lantaff', '8946564516');
insert into Client (id, firstName, lastName, phoneNumber) values (94, 'Liane', 'Antonias', '6326261936');
insert into Client (id, firstName, lastName, phoneNumber) values (95, 'Cristian', 'Turbayne', '5272690120');
insert into Client (id, firstName, lastName, phoneNumber) values (96, 'Ingra', 'Aikman', '6273525068');
insert into Client (id, firstName, lastName, phoneNumber) values (97, 'Nisse', 'Halbord', '9538660286');
insert into Client (id, firstName, lastName, phoneNumber) values (98, 'Loralyn', 'Wallwork', '9458744250');
insert into Client (id, firstName, lastName, phoneNumber) values (99, 'Claretta', 'Antoshin', '6925302328');
insert into Client (id, firstName, lastName, phoneNumber) values (100, 'Ludvig', 'Worledge', '7242179964');

insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (60, 21, 66, '2022-03-28');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (93, 1, 40, '2023-02-03');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (8, 30, 95, '2021-12-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (58, 10, 46, '2023-06-27');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (32, 75, 100, '2022-11-01');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (66, 36, 20, '2023-08-05');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (97, 18, 95, '2023-02-01');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (41, 19, 20, '2023-05-16');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (85, 41, 32, '2022-08-27');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (20, 16, 3, '2022-10-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (91, 80, 78, '2022-05-20');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (96, 93, 29, '2022-03-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (45, 84, 80, '2022-08-22');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (96, 32, 78, '2023-01-05');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (59, 93, 35, '2022-10-10');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (40, 49, 77, '2022-10-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (90, 66, 31, '2023-05-18');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (11, 63, 55, '2021-10-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (10, 89, 74, '2023-04-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (79, 1, 33, '2022-01-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (26, 5, 67, '2023-05-05');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (93, 37, 78, '2022-05-22');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (88, 45, 69, '2022-11-22');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (7, 57, 3, '2023-07-19');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (96, 89, 88, '2022-12-06');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (36, 69, 15, '2022-08-12');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (57, 1, 81, '2022-08-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (41, 37, 42, '2022-03-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (50, 82, 6, '2021-12-31');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (52, 2, 47, '2023-11-05');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (6, 32, 58, '2022-09-02');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (23, 58, 71, '2023-10-31');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (9, 27, 73, '2021-10-11');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (34, 82, 36, '2023-09-07');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (68, 96, 29, '2023-02-19');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (61, 68, 38, '2023-02-16');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (90, 24, 39, '2022-04-19');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (67, 38, 83, '2023-01-05');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (31, 54, 78, '2021-12-01');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (28, 81, 82, '2022-03-27');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (71, 26, 23, '2022-10-27');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (39, 26, 11, '2022-06-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (42, 73, 98, '2022-09-26');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (63, 92, 75, '2023-08-07');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (98, 38, 26, '2023-09-06');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (38, 79, 52, '2022-01-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (29, 38, 13, '2023-08-26');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (40, 29, 94, '2022-11-02');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (27, 5, 38, '2022-01-07');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (80, 97, 7, '2022-12-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (70, 41, 11, '2022-06-21');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (88, 19, 14, '2022-10-23');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (50, 6, 41, '2021-10-12');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (79, 59, 97, '2022-09-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (73, 11, 98, '2022-07-28');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (59, 25, 96, '2023-06-22');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (70, 29, 31, '2022-10-06');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (84, 34, 84, '2022-01-16');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (23, 13, 51, '2022-03-16');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (69, 48, 48, '2022-03-13');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (23, 72, 12, '2023-08-04');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (84, 100, 58, '2022-08-13');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (16, 19, 87, '2023-02-20');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (45, 37, 99, '2022-01-01');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (74, 17, 53, '2022-05-26');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (31, 97, 55, '2022-07-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (5, 56, 65, '2023-07-18');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (32, 54, 2, '2023-06-13');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (28, 14, 38, '2021-12-07');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (16, 45, 33, '2022-08-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (80, 86, 54, '2022-11-28');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (67, 51, 72, '2023-05-31');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (41, 48, 98, '2023-01-28');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (33, 75, 39, '2023-06-27');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (51, 97, 44, '2023-02-24');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (81, 62, 37, '2022-09-25');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (5, 75, 88, '2022-09-02');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (94, 71, 40, '2023-06-30');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (53, 61, 10, '2022-04-24');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (68, 18, 26, '2023-06-07');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (86, 20, 60, '2023-05-08');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (25, 44, 32, '2021-12-09');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (48, 63, 89, '2023-04-10');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (54, 95, 33, '2023-03-09');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (14, 96, 62, '2022-12-06');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (88, 40, 58, '2023-06-04');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (98, 42, 49, '2023-07-25');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (18, 46, 5, '2021-10-23');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (93, 26, 74, '2021-10-20');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (4, 89, 48, '2022-08-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (33, 39, 81, '2022-04-24');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (26, 56, 25, '2023-10-09');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (59, 60, 32, '2023-09-06');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (91, 15, 5, '2022-10-15');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (6, 88, 91, '2022-03-02');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (33, 8, 25, '2023-08-19');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (99, 81, 46, '2023-11-02');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (62, 45, 94, '2023-05-16');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (32, 22, 13, '2023-03-17');
insert into Purchase (bookId, clientId, numberSold, dateOfPurchase) values (68, 21, 57, '2023-07-31');


SELECT * FROM Book;
SELECT * FROM Client;
SELECT * FROM Purchase;
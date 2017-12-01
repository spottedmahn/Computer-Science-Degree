VERSION 5.00
Object = "{67397AA1-7FB1-11D0-B148-00A0C922E820}#6.0#0"; "MSADODC.OCX"
Begin VB.Form Form1 
   Caption         =   "Form1"
   ClientHeight    =   3195
   ClientLeft      =   60
   ClientTop       =   345
   ClientWidth     =   4680
   LinkTopic       =   "Form1"
   ScaleHeight     =   3195
   ScaleWidth      =   4680
   StartUpPosition =   3  'Windows Default
   Begin VB.TextBox Text7 
      DataField       =   "zip"
      DataSource      =   "Adodc1"
      Height          =   375
      Left            =   2280
      TabIndex        =   6
      Text            =   "Text7"
      Top             =   2160
      Width           =   2175
   End
   Begin VB.TextBox Text6 
      DataField       =   "state"
      DataSource      =   "Adodc1"
      Height          =   375
      Left            =   240
      TabIndex        =   5
      Text            =   "Text6"
      Top             =   2160
      Width           =   1935
   End
   Begin VB.TextBox Text5 
      DataField       =   "city"
      DataSource      =   "Adodc1"
      Height          =   405
      Left            =   240
      TabIndex        =   4
      Text            =   "Text5"
      Top             =   1680
      Width           =   4215
   End
   Begin VB.TextBox Text4 
      DataField       =   "address"
      DataSource      =   "Adodc1"
      Height          =   405
      Left            =   240
      TabIndex        =   3
      Text            =   "Text4"
      Top             =   1200
      Width           =   4215
   End
   Begin VB.TextBox Text3 
      DataField       =   "phone"
      DataSource      =   "Adodc1"
      Height          =   405
      Left            =   240
      TabIndex        =   2
      Text            =   "Text3"
      Top             =   720
      Width           =   4215
   End
   Begin VB.TextBox Text2 
      DataField       =   "au_lname"
      DataSource      =   "Adodc1"
      Height          =   405
      Left            =   2160
      TabIndex        =   1
      Text            =   "Text2"
      Top             =   240
      Width           =   2295
   End
   Begin VB.TextBox Text1 
      DataField       =   "au_fname"
      DataSource      =   "Adodc1"
      Height          =   405
      Left            =   240
      TabIndex        =   0
      Text            =   "Text1"
      Top             =   240
      Width           =   1815
   End
   Begin MSAdodcLib.Adodc Adodc1 
      Height          =   375
      Left            =   120
      Top             =   2760
      Width           =   4455
      _ExtentX        =   7858
      _ExtentY        =   661
      ConnectMode     =   0
      CursorLocation  =   3
      IsolationLevel  =   -1
      ConnectionTimeout=   15
      CommandTimeout  =   30
      CursorType      =   3
      LockType        =   3
      CommandType     =   1
      CursorOptions   =   0
      CacheSize       =   50
      MaxRecords      =   0
      BOFAction       =   0
      EOFAction       =   0
      ConnectStringType=   1
      Appearance      =   1
      BackColor       =   -2147483643
      ForeColor       =   -2147483640
      Orientation     =   0
      Enabled         =   -1
      Connect         =   "Provider=SQLOLEDB.1;Persist Security Info=False;User ID=sa;Initial Catalog=pubs;Data Source=192.168.42.252"
      OLEDBString     =   "Provider=SQLOLEDB.1;Persist Security Info=False;User ID=sa;Initial Catalog=pubs;Data Source=192.168.42.252"
      OLEDBFile       =   ""
      DataSourceName  =   ""
      OtherAttributes =   ""
      UserName        =   ""
      Password        =   ""
      RecordSource    =   "select * from authors"
      Caption         =   "Adodc1"
      BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      _Version        =   393216
   End
End
Attribute VB_Name = "Form1"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

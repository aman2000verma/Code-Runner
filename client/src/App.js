import React from "react";
import {Button, Combobox, Heading, Overlay, Pane, PlayIcon, Spinner, Textarea, WarningSignIcon} from "evergreen-ui";
import axios from "axios";
import {endpoints} from "./Api";
import LangMeta from "./lang-meta.json";

function App() {
  const [compilers, setCompilers] = React.useState([]);
  const [selectedCompiler, setSelectedCompiler] = React.useState("");
  const [showOverlay, setIsShown] = React.useState(false);
  const [ip, setIP] = React.useState("");
  const [code, setCode] = React.useState("");
  const [out, setOut] = React.useState("");
  const [notice, setNotice] = React.useState(null);

  const getIPData = async () => {
    await axios.get("https://geolocation-db.com/json/").then((res) => {
      setIP(res.data.IPv4);
    });
  };

  const getCompilers = async () => {
    await axios
      .get(endpoints.getCompilers)
      .then((res) => {
        setCompilers([...res.data]);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const executeCode = async () => {
    const body = {
      ip: ip,
      syntax: code,
      compiler: selectedCompiler,
      input: ""
    };

    console.log(body);

    await axios
      .post(endpoints.execute, body)
      .then((res) => {
        setOut(res.data.output);
      })
      .catch((err) => {
        console.error(err);
      });
    setIsShown(false);
  };

  const handleCodeChange = (ev) => {
    setCode(ev.target.value);
  };

  const loadMeta = (compiler) => {
    if (LangMeta[compiler]) {
      setNotice(LangMeta[compiler].notice);
    } else {
      setNotice(null);
    }
    setCode(LangMeta[compiler].boilerCode);
  };

  const resetCode = () => {
    setCode(LangMeta[selectedCompiler].boilerCode);
  };

  const reformatCode = () => {};

  React.useEffect(() => {
    getCompilers();
    getIPData();
  }, []);

  return (
    <>
      <Pane className="head" display="flex" paddingTop={16}>
        <Pane flex={1} alignItems="center" display="flex">
          <Heading marginLeft={150} size={800}>
            Code Runner
          </Heading>
        </Pane>
        <Pane display="flex">
          <Combobox
            initialSelectedItem={compilers[0]}
            items={compilers}
            onChange={(changed) => {
              setSelectedCompiler(changed);
              loadMeta(changed);
            }}
          />
          {selectedCompiler === null ? (
            <Button marginRight={150} disabled={true}>
              <PlayIcon size={40} />
            </Button>
          ) : (
            <Button
              marginRight={150}
              onClick={() => {
                setIsShown(true);
                executeCode();
              }}
            >
              <PlayIcon size={40} />
            </Button>
          )}
        </Pane>
      </Pane>
      <Overlay
        shouldCloseOnClick={false}
        shouldCloseOnEscapePress={false}
        isShown={showOverlay}
      >
        <Pane
          display="flex"
          alignItems="center"
          justifyContent="center"
          height={400}
        >
          <Spinner size={100} />
        </Pane>
      </Overlay>
      <Pane
        className="content"
        display="flex"
        flexDirection="column"
        paddingTop={16}
        marginLeft={100}
        marginRight={100}
      >
        <Textarea
          className="syntax"
          placeholder="Your code goes here..."
          resize="none"
          minHeight={"25rem"}
          onChange={handleCodeChange}
          onKeyUp={handleCodeChange}
          value={code}
        />
        {notice ? (
          <Pane
            display="flex"
            alignContent="center"
            flexDirection="row"
            marginTop={16}
            backgroundColor="#FFEBCD"
            justifyContent="center"
            paddingTop={8}
            paddingBottom={8}
          >
            <WarningSignIcon color="warning" size={20} marginRight={5} />
            {LangMeta["Java 8"].notice}
          </Pane>
        ) : (
          <></>
        )}

        <Pane display="flex" flexDirection="row" paddingTop={16}>
          <Button
            appearance="secondary"
            marginRight={16}
            onClick={() => resetCode()}
          >
            Reset
          </Button>
          <Button onClick={() => reformatCode()}>Reformat</Button>
        </Pane>
        <Textarea
          className="output"
          marginTop={16}
          placeholder="Output"
          resize="none"
          minHeight={"10rem"}
          disabled={true}
          value={out}
        />
      </Pane>
    </>
  );
}

export default App;

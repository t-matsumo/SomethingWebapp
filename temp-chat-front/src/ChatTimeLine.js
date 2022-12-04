import React, { useState, useEffect } from 'react';

function ChatArea(props) {
  return (
    <div>
      {props.chatList.map((d) =>
        <div key={d.id} >
          <div>{d.user.name}</div>
          <div>{d.sentAt.toLocaleDateString()}</div>
          <div>{d.contents}</div>
          <hr />
        </div>
      )}
    </div>
  )
}

function TextForm(props) {
  function onSubmit(e) {
    e.preventDefault()
    props.onSubmit(props.chatText)
  }

  function onChange(e) {
    props.onChange(e.target.value)
  }

  return (
    <form onSubmit={onSubmit}>
      <textarea value={props.chatText} onChange={onChange} placeholder="送信したい内容を入力" />
      <input type="submit" value="送信する" />
    </form >
  )
}

function ChatTimeLine() {
  const [chatText, setChatText] = useState("");
  const [data, setData] = useState([]);

  useEffect(() => {
    let ignore = false;

    fetch('http://localhost:8080/api/chatmessage/')
      .then((response) => response.json())
      .then((data) => {
        if (!ignore) {
          let dataArray = data.map((d) => {
            return {
              user: {
                name: d.sender.name
              },
              contents: d.content,
              sentAt: new Date(d.createdAt),
              id: d.id
            }
          })
          setData(dataArray)
        }
      });

    return () => {
      ignore = true;
    };
  }, []);

  function onSubmit(text) {
    console.log(text)
    const sendData = {
      content: text,
      sender: { name: "React User" }
    }

    fetch('http://localhost:8080/api/chatmessage/', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(sendData),
    })
      .then((response) => response.json())
      .then((dat) => {
        let d = {
          user: {
            name: dat.sender.name
          },
          contents: dat.content,
          sentAt: new Date(dat.createdAt),
          id: dat.id
        }
        setData([...data, d])
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }

  function onChange(text) {
    setChatText(text)
  }

  return (
    <div>
      <h1>Room Name</h1>
      <ChatArea chatList={data} />
      <TextForm
        onSubmit={onSubmit}
        chatText={chatText}
        onChange={onChange}
      />
    </div>
  );
}
export default ChatTimeLine;

// let rawdata = [
          //   {
          //     user: {
          //       name: "nameA"
          //     },
          //     contents: "contentcontentcontentcontent",
          //     sentAt: new Date(),
          //     id: 1
          //   },
          //   {
          //     user: {
          //       name: "nameB"
          //     },
          //     contents: "aaaaacontentcontentcontentcontent",
          //     sentAt: new Date(),
          //     id: 2
          //   },
          //   {
          //     user: {
          //       name: "nameA"
          //     },
          //     contents: "vvvvvcontentcontentcontentcontent",
          //     sentAt: new Date(),
          //     id: 3
          //   }
          // ]
